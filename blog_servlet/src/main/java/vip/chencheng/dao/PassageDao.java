package vip.chencheng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vip.chencheng.connectorDatabase.DBUtilConnection;
import vip.chencheng.entity.AllKindInfo;
import vip.chencheng.entity.CommentInfo;
import vip.chencheng.entity.PassageInfo;
import vip.chencheng.tool.Tool;

public class PassageDao {
	private Connection conn=null;
	private Statement stmt=null;
	private Statement stmt2=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private ResultSet rs2=null;
	
	
	/*
	 * 获取种类类别，三参数分别为
	 * 是否过滤AKstate（isfilter==true过滤，为false不过滤），
	 * 类名标志AKkind，
	 * 几级目录isroot,0为father值为0;1为非father；-1为全部内容*/
	public List<AllKindInfo> getAllKindList(boolean isfilter,int akkind,int isroot) {
		
		conn=DBUtilConnection.getConnectionImportData();
		List<AllKindInfo> pass_kindList=new ArrayList<AllKindInfo>();

		String sql=null;
		if(isfilter){
			if(isroot==0){
				sql="select * from pass_kind where AKstate=0 and AKkind="+akkind+" and AKfather=0 order by AKid asc";
			}else if(isroot==1){
				sql="select * from pass_kind where AKstate=0 and AKkind="+akkind+" and AKfather<>0 order by AKid asc";
			}else{
				sql="select * from pass_kind where AKstate=0 and AKkind="+akkind+" order by AKid asc";
			}
		}else{
			if(isroot==0){
				sql="select * from pass_kind where AKkind="+akkind+" and AKfather=0 order by AKid asc";
			}else if(isroot==1){
				sql="select * from pass_kind where AKkind="+akkind+" and AKfather<>0 order by AKid asc";
			}else{
				sql="select * from pass_kind where AKkind="+akkind+" order by AKid asc";
			}
		}
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AllKindInfo pass_kindInfo=new AllKindInfo();
				
				pass_kindInfo.setAkid(rs.getInt(1));
				pass_kindInfo.setAkvalue(rs.getString(2));
				pass_kindInfo.setAkdetail(rs.getString(3));
				pass_kindInfo.setAkstate(rs.getInt(4));
				pass_kindInfo.setAkfather(rs.getInt(5));
				pass_kindInfo.setAkkind(rs.getInt(6));

				pass_kindList.add(pass_kindInfo);
			}
		} catch (Exception e) {
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return pass_kindList;
	}	
	

	/*
	 * 获取文章列表
	 * kind按种类筛选。0表示无此字段筛选
	 * select选择的关键字，查询内容时使用，null表示无此字段。
	 * pageIndex当前分页数,当为0时默认第一页
	 * pageSize每页内容数。为0默认20个
	 * belong按归属筛选。0表示无此字段
	 * */
	public List<Integer> getPassageInfoList(int kind,
			String select, int pageIndex, int pageSize, int belong, List<PassageInfo> pass_passageInfoList) {
		
		List<Integer> indexAndPage = new ArrayList<Integer>();
		String sql=null;
		
		String whereSql = "where 1 = 1 ";
		if(select != null) {
			whereSql += "and Ptitle like '%"+select+"%' ";
		}
		if(belong!=0) {
			whereSql += "and Pbelong = "+belong+" ";
		}
		if(kind!=0) {
			whereSql += "and Pkind="+kind;
		}
		if(pageIndex<=0) {
			pageIndex = 0;
		}else {
			pageIndex--;
		}
		if(pageSize<=0) {
			pageSize = 15;
		}
		
		conn=DBUtilConnection.getConnectionImportData();
		//List<PassageInfo> pass_passageInfoList=new ArrayList<PassageInfo>();
		try {
			stmt = conn.createStatement();
			
			//获取记录总数
			String getCountSql = "select count(*) from pass_passage " + whereSql;
			rs = stmt.executeQuery(getCountSql);
			rs.next();
			int countAll = rs.getInt(1);	//获取满足条件记录总数
			rs.close();
			
			//计算分页数
			if(countAll/pageSize <= pageIndex) {
				//页码过大
				pageIndex = 0;
			}
			
			//将分页信息和当前页传出
			indexAndPage.add(new Integer(pageIndex + 1));//当前页面
			indexAndPage.add(new Integer((int)Math.ceil((float)countAll/pageSize)) );//总页数
			indexAndPage.add(pageSize);//每页个数
			indexAndPage.add(kind);//种类编码
			indexAndPage.add(belong);//归属

			sql="select * from pass_passage " + whereSql +" order by Pnumber desc limit " + pageIndex*pageSize + ", " + pageSize;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				PassageInfo pass_passageInfo=new PassageInfo();
				pass_passageInfo.setPnumber(rs.getInt(1));
				pass_passageInfo.setPtitle(rs.getString(2));
				pass_passageInfo.setPtime(rs.getDate(3));
				pass_passageInfo.setPlabel(rs.getString(4));
				pass_passageInfo.setPcount(rs.getString(5));
				pass_passageInfo.setPcoming(rs.getString(6));
				pass_passageInfo.setPcomingUrl(rs.getString(7));
				pass_passageInfo.setPimage(rs.getString(8));
				pass_passageInfo.setPkind(rs.getInt(9));
				pass_passageInfo.setPbelong(rs.getInt(10));
				pass_passageInfo.setPdescribe(rs.getString(11));

				String sqlgetName="select AKvalue from pass_kind where AKid="+pass_passageInfo.getPkind();
				try {
					stmt2 = conn.createStatement();
					rs2 = stmt2.executeQuery(sqlgetName);
					rs2.next();
					pass_passageInfo.setPkindName(rs2.getString(1));
				}finally{
					DBUtilConnection.closeAll(rs2, stmt2, null, null);
				}
				//获取归属名
				String sqlgetBelongName="select AKvalue from pass_kind where AKid="+pass_passageInfo.getPbelong();
				try {
					stmt2 = conn.createStatement();
					rs2 = stmt2.executeQuery(sqlgetBelongName);
					rs2.next();
					pass_passageInfo.setPbelongName(rs2.getString(1));
				}finally{
					DBUtilConnection.closeAll(rs2, stmt2, null, null);
				}
				pass_passageInfoList.add(pass_passageInfo);
				
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return indexAndPage;
	}
	
	/**
	 * 通过文章id获取上一条和下一条信息，并给出10条推荐
	 * @param pnumber
	 * @return
	 */
	public List<PassageInfo> getPassageRecommend(int pnumber) {
		String sql=null;
		
		List<PassageInfo> pass_passageInfoList=new ArrayList<PassageInfo>();
		
		List<PassageInfo> pass_passageInfoList_temp=new ArrayList<PassageInfo>();
		PassageInfo passageInfo = new PassageInfo();
		passageInfo.setPnumber(0);
		
		//上一条
		sql="select * from pass_passage where Pnumber < "+pnumber+" order by Pnumber desc limit 0,1";
		pass_passageInfoList_temp = getPassageInfoList(sql);
		if(pass_passageInfoList_temp.isEmpty()) {
			pass_passageInfoList.add(0, passageInfo);
		}else {
			pass_passageInfoList.add(0, pass_passageInfoList_temp.get(0));
		}
		
		//下一条
		sql="select * from pass_passage where Pnumber > "+pnumber+" order by Pnumber asc limit 0,1";
		pass_passageInfoList_temp = getPassageInfoList(sql);
		if(pass_passageInfoList_temp.isEmpty()) {
			pass_passageInfoList.add(1, passageInfo);
		}else {
			pass_passageInfoList.add(1, getPassageInfoList(sql).get(0));
		}
		
		//随机10条
		sql="select * from pass_passage where Pnumber < "+(pnumber-5)+" or Pnumber > "+(pnumber+5)+" order by Pnumber asc limit 0,10";
		pass_passageInfoList.addAll(getPassageInfoList(sql));
	
		return pass_passageInfoList;
	}
	
	public List<PassageInfo> getPassageInfoList(String sql){
		List<PassageInfo> pass_passageInfoList=new ArrayList<PassageInfo>();
		try {
			
			conn=DBUtilConnection.getConnectionImportData();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				PassageInfo pass_passageInfo=new PassageInfo();
				pass_passageInfo.setPnumber(rs.getInt(1));
				pass_passageInfo.setPtitle(rs.getString(2));
				pass_passageInfo.setPtime(rs.getDate(3));
				pass_passageInfo.setPlabel(rs.getString(4));
				pass_passageInfo.setPcount(rs.getString(5));
				pass_passageInfo.setPcoming(rs.getString(6));
				pass_passageInfo.setPcomingUrl(rs.getString(7));
				pass_passageInfo.setPimage(rs.getString(8));
				pass_passageInfo.setPkind(rs.getInt(9));
				pass_passageInfo.setPbelong(rs.getInt(10));
				pass_passageInfo.setPdescribe(rs.getString(11));

				String sqlgetName="select AKvalue from pass_kind where AKid="+pass_passageInfo.getPkind();
				try {
					stmt2 = conn.createStatement();
					rs2 = stmt2.executeQuery(sqlgetName);
					rs2.next();
					pass_passageInfo.setPkindName(rs2.getString(1));
				}finally{
					DBUtilConnection.closeAll(rs2, stmt2, null, null);
				}
				//获取归属名
				String sqlgetBelongName="select AKvalue from pass_kind where AKid="+pass_passageInfo.getPbelong();
				try {
					stmt2 = conn.createStatement();
					rs2 = stmt2.executeQuery(sqlgetBelongName);
					rs2.next();
					pass_passageInfo.setPbelongName(rs2.getString(1));
				}finally{
					DBUtilConnection.closeAll(rs2, stmt2, null, null);
				}				
				
				
				pass_passageInfoList.add(pass_passageInfo);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		
		return pass_passageInfoList;
	}

	/*通过id获得一个文章信息*/
	public PassageInfo getPassageById(int pnumber) {
		String sql="select * from pass_passage where Pnumber="+pnumber;
		conn=DBUtilConnection.getConnectionImportData();
		PassageInfo pass_passageInfo=new PassageInfo();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			pass_passageInfo.setPnumber(rs.getInt(1));
			pass_passageInfo.setPtitle(rs.getString(2));
			pass_passageInfo.setPtime(rs.getDate(3));
			pass_passageInfo.setPlabel(rs.getString(4));
			pass_passageInfo.setPcount(rs.getString(5));
			pass_passageInfo.setPcoming(rs.getString(6));
			pass_passageInfo.setPcomingUrl(rs.getString(7));
			pass_passageInfo.setPimage(rs.getString(8));
			pass_passageInfo.setPkind(rs.getInt(9));
			pass_passageInfo.setPbelong(rs.getInt(10));
			pass_passageInfo.setPdescribe(rs.getString(11));
			

			String sqlgetName="select AKvalue from pass_kind where AKid="+pass_passageInfo.getPkind();
			try {
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sqlgetName);
				rs2.next();
				pass_passageInfo.setPkindName(rs2.getString(1));
			}finally{
				DBUtilConnection.closeAll(rs2, stmt2, null, null);
			}
			String sqlgetBelongName="select AKvalue from pass_kind where AKid="+pass_passageInfo.getPbelong();
			try {
				stmt2 = conn.createStatement();
				rs2 = stmt2.executeQuery(sqlgetBelongName);
				rs2.next();
				pass_passageInfo.setPbelongName(rs2.getString(1));
			}finally{
				DBUtilConnection.closeAll(rs2, stmt2, null, null);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return pass_passageInfo;
	}

	/*获取某一文章的评论信息*/
	public List<CommentInfo> getCommentInfoListByPid(int pnumber) {
		String sql="select * from pass_comment where Pnumber="+pnumber;
		conn=DBUtilConnection.getConnectionImportData();
		List<CommentInfo> pass_commentInfoList=new ArrayList<CommentInfo>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				CommentInfo pass_commentInfo=new CommentInfo();
				pass_commentInfo.setCnumber(rs.getInt(1));
				pass_commentInfo.setCcount(rs.getString(2));
				pass_commentInfo.setCtime(rs.getDate(3));
				pass_commentInfo.setCtitle(rs.getString(4));
				pass_commentInfo.setPnumber(rs.getInt(5));
				
				pass_commentInfoList.add(pass_commentInfo);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return pass_commentInfoList;
	}

	/*修改文章信息*/
	public void changePassageInfo(int number, String title, String image,
			String keyword, String content, String coming, String comingUrl,
			int pass_passageKind,int pbelong) {
		conn=DBUtilConnection.getConnectionImportData();
		String sql="update pass_passage set Ptitle=?,Plabel=?,Pcount=?,Pcoming=?,PcomingUrl=?,Pimage=?,Pkind=?,Pbelong=?,Pdescribe=? where Pnumber=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, keyword);
			pstmt.setString(3, content);
			pstmt.setString(4, coming);
			pstmt.setString(5, comingUrl);
			pstmt.setString(6, image);
			pstmt.setInt(7, pass_passageKind);
			pstmt.setInt(8, pbelong);
			String describe = Tool.delHTMLTag(content);
			if(describe.length()>=255) {
				pstmt.setString(9, describe.substring(0, 255));
			}else {
				pstmt.setString(9, describe);
			}
			pstmt.setInt(10,number);
			pstmt.executeUpdate();
			
		} catch (Exception e) {System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
	}

	/*新增信息*/
	public void addPassageInfo(String title, String image, String keyword,
			String content, String coming, String comingUrl, int pass_passageKind,int passageBelong) {
		conn=DBUtilConnection.getConnectionImportData();
		Timestamp ctime = new java.sql.Timestamp(new java.util.Date().getTime());
		
		String sql="insert into pass_passage(Ptitle,Plabel,Pcount,Pcoming,PcomingUrl,Pimage,Pkind,Ptime,Pbelong,Pdescribe) values(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, keyword);
			pstmt.setString(3, content);
			pstmt.setString(4, coming);
			pstmt.setString(5, comingUrl);
			pstmt.setString(6, image);
			pstmt.setInt(7, pass_passageKind);
			pstmt.setTimestamp(8, ctime);
			pstmt.setInt(9, passageBelong);
			String describe = Tool.delHTMLTag(content);
			if(describe.length()>=255) {
				pstmt.setString(10, describe.substring(0, 255));
			}else {
				pstmt.setString(10, describe);
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
	}

	/*新增评论信息*/
	public void addCommentInfo(int pnumber, String title,String content) {
		conn=DBUtilConnection.getConnectionImportData();
		Timestamp ctime = new java.sql.Timestamp(new java.util.Date().getTime());
		
		String sql="insert into pass_comment(ccount,ctitle,pnumber,Ctime) values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, title);
			pstmt.setInt(3, pnumber);
			pstmt.setTimestamp(4,ctime);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
		
	}

	/*获取一条评论信息*/
	public CommentInfo getCommentById(int cnumber) {
		String sql="select * from pass_comment where cnumber="+cnumber;
		conn=DBUtilConnection.getConnectionImportData();
		CommentInfo pass_commentInfo=new CommentInfo();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
				
			pass_commentInfo.setCnumber(rs.getInt(1));
			pass_commentInfo.setCcount(rs.getString(2));
			pass_commentInfo.setCtime(rs.getDate(3));
			pass_commentInfo.setCtitle(rs.getString(4));
			pass_commentInfo.setPnumber(rs.getInt(5));
				
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return pass_commentInfo;
	}

	/*改变评论信息*/
	public void changeCommentInfo(int cnumber, String ctitle, String content) {
		conn=DBUtilConnection.getConnectionImportData();
		String sql="update pass_comment set ccount=?,ctitle=? where cnumber=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, ctitle);
			pstmt.setInt(3,cnumber);
			pstmt.executeUpdate();
			
		} catch (Exception e) {System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
		
	}


	public void deletePassageInfo(int number) {
		conn=DBUtilConnection.getConnectionImportData();
		String sql="delete from pass_passage where Pnumber=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,number);
			pstmt.executeUpdate();
			
		} catch (Exception e) {System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
	}

}

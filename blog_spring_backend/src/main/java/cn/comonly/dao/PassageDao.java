package cn.comonly.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.comonly.connectionDatabase.DBUtilConnection;
import cn.comonly.pojo.Passage;

@Component
public class PassageDao {
	private Connection conn=null;
	private Statement stmt=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;


	public List<Passage> getPassageList(String sql){
		List<Passage> pass_passageInfoList=new ArrayList<Passage>();
		try {
			
			conn=DBUtilConnection.getConnectionImportData();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				Passage pass_passageInfo=new Passage();
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
	public Passage getPassageById(int pnumber) {
		String sql="select * from pass_passage where Pnumber="+pnumber;
		conn=DBUtilConnection.getConnectionImportData();
		Passage pass_passageInfo=new Passage();
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

			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return pass_passageInfo;
	}

	/*修改文章信息*/
	public void changePassage(int number, String title, String image,
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
			pstmt.setString(9, "");
			pstmt.setInt(10,number);
			pstmt.executeUpdate();
			
		} catch (Exception e) {System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
	}

	/*新增信息*/
	public void addPassage(String title, String image, String keyword,
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
			pstmt.setString(10, "");
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
	}


	public void deletePassage(int number) {
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

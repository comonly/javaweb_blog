package vip.chencheng.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import vip.chencheng.connectorDatabase.DBUtilConnection;
import vip.chencheng.entity.AllKindInfo;

public class KindManageDao {
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
		List<AllKindInfo> imp_allKindList=new ArrayList<AllKindInfo>();

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
				AllKindInfo imp_allKindInfo=new AllKindInfo();
				
				imp_allKindInfo.setAkid(rs.getInt(1));
				imp_allKindInfo.setAkvalue(rs.getString(2));
				imp_allKindInfo.setAkdetail(rs.getString(3));
				imp_allKindInfo.setAkstate(rs.getInt(4));
				imp_allKindInfo.setAkfather(rs.getInt(5));
				imp_allKindInfo.setAkkind(rs.getInt(6));

				imp_allKindList.add(imp_allKindInfo);
			}
		} catch (Exception e) {
			return null;
		}finally{
			DBUtilConnection.closeAll(rs, stmt, null, conn);
		}
		return imp_allKindList;
	}

	/*修改账单详情目录的信息*/
	public void changeAllKindInfo(int akid, String akvalue,
			String akdetail, int akstate,int akfather,int akkind) {
		conn=DBUtilConnection.getConnectionImportData();
		String sql="update pass_kind set AKvalue=?,AKdetail=?,AKstate=?,AKfather=?,AKkind=? where AKid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, akvalue);
			pstmt.setString(2, akdetail);
			pstmt.setInt(3, akstate);
			pstmt.setInt(4, akfather);
			pstmt.setInt(5, akkind);
			pstmt.setInt(6, akid);
			pstmt.executeUpdate();

		} catch (Exception e) {
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}

	}



	/*添加新类别信息*/
	public void addKindInfo(int akkind, String akvalue, int akfather,
			String akdetail, int akstate) {
		conn=DBUtilConnection.getConnectionImportData();
		String sql="insert into pass_kind(AKvalue,AKdetail,AKstate,AKfather,AKkind) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, akvalue);
			pstmt.setString(2, akdetail);
			pstmt.setInt(3, akstate);
			pstmt.setInt(4, akfather);
			pstmt.setInt(5, akkind);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			DBUtilConnection.closeAll(null, null, pstmt, conn);
		}
		
	}

	

	
}

package vip.chencheng.connectorDatabase;

import java.sql.Connection;
import java.sql.DriverManager;	//自己编译的一个单文件
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DBUtilConnection {
	
	private static ReentrantLock reentrantLock = new ReentrantLock();
	static Connection conn = null;
	
	public static Connection getConnectionImportData() {
		try {
			if(conn == null || conn.isClosed()) {
				try{
			        // 注册 JDBC 驱动
			        Class.forName("com.mysql.jdbc.Driver");
			        // 打开连接
			        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passage_low_for_git?useUnicode=true&characterEncoding=UTF-8","root","xixichencheng");
			        System.out.println("创建数据库连接：" + conn);
			    }catch(SQLException se){
			        // 处理 JDBC 错误
			        se.printStackTrace();
			    }catch(Exception e){
			        e.printStackTrace();
			    }
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//判断conn是否被占用
		boolean lookResult = false;
		try {
			//等待10秒
			lookResult = reentrantLock.tryLock(10, TimeUnit.SECONDS);
			//System.out.println("数据库连接加锁：" + reentrantLock);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(lookResult) {
			return conn;
		}else {
			//无法获取连接锁
			System.out.println("连接被占用，出现异常！");
			return null;
		}
		
	}
	 
	public static void closeAll(ResultSet rs,Statement stmt,PreparedStatement pstmt,Connection conn){
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null) {
				//System.out.println("数据库连接解锁：" + reentrantLock);
				reentrantLock.unlock();
				//conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

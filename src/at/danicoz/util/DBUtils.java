package at.danicoz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	//��ȡ���ݿ�����
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
			String user = "scott";
			String password = "Danicoz326";
			conn = DriverManager.getConnection(url,user,password);
			
		}
		catch(SQLException e){
			 e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	//��������
	public static void beginTransaction(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ύ����
	public static void commit(Connection conn){
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ع�����
	public static void rollback(Connection conn){
		
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ر�����
	public static void closeConnection(Connection conn){
		
			try {
				if(conn!=null){
				conn.close();
				}
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	//�ر� statement ����
	public static void closeStatement(ResultSet rs,Statement stm){
		try{
			if(rs!=null){
				rs.close();
			}
			if(stm!=null){
				stm.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}//class

package mms.royal.db;

import java.sql.*;

public class JdbcUtils {

	static {
		//클래스가 로딩될 때 한번 호출되는 영역
		//Class.forName() : 특정 클래스를 메모리로 로딩하는 역할을 수행하는 메서드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
			
	}
	
	// CONNECTION 객체를 생성해서 리턴시켜주는 메서드
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@192.168.0.107:1521:orcl";
		String user = "messenger";
		String password = "1234";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	//close 객체 생성
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void commit() {
		
	}

}

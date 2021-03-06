package com.javaex.sqlformat;

//0. import java.sql.*;
import java.sql.*;

public class BaseFormatTest {
	
	//try대신 throws로 예외처리 (불안정, 직접 처리 불가)
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		
		// 3. SQL문 준비 / 바인딩 / 실행
		//준비
		String query = "";
		query += " insert into author";
		query += " values(seq_auth.nextval, ?, ?)";
		
		//바인딩
		pstmt = conn.prepareStatement(query); //쿼리를 문자열로 만들기;
		pstmt.setString(1, "김영하"); // ? #1 (순서 중요)
		pstmt.setString(2, "알쓸신잡"); // ? #2
		
		//실행
		int count = pstmt.executeUpdate();
		
		// 4.결과처리
		System.out.println(count + " 행 이(가) 삽입되었습니다.");
		
	}
}

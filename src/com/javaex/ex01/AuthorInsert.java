package com.javaex.ex01;
import java.sql.*;


public class AuthorInsert {
	public static void main(String[] args) {
		//0. import java.sql.*;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		//1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		//3. SQL문 준비 / 바인딩 / 실행
			//SQL 문 준비
			String query = "";
			query += " insert into author";
			query += " values(seq_author_id.nextval, ?, ?)";
			
			//바인딩
			pstmt = conn.prepareStatement(query); //쿼리를 문자열로 만들기;
			pstmt.setString(1, "김영하"); // ? #1 (순서 중요)
			pstmt.setString(2, "알쓸신잡"); // ? #2
			
			//실행
			//자동 commit 주의
			int count = pstmt.executeUpdate();
			
		//4.결과처리
			System.out.println(count + " 행 이(가) 삽입되었습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			//5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
}

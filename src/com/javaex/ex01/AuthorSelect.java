package com.javaex.ex01;

import java.sql.*;

public class AuthorSelect {
public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//준비
			String query = "";
			query += " select	author_id,";
			query += " 			author_name,";
			query += " 			author_desc desc";
			query += " from author";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			while(rs.next()) {
				int authorId = rs.getInt(1); //컬럼 시작 자리는 항상 1 (author_id)
				String authorName = rs.getString("author_name"); //can also use column name instead of position
				String authorDesc = rs.getString("desc"); //must use named column if named
				
				System.out.println(authorId + ", " + authorName + ", " + authorDesc);
			}
						
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
			try {
			if (rs != null) {
				rs.close();
			}
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

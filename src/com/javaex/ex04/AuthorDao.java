package com.javaex.ex04;

import java.sql.*;
import java.util.*;

public class AuthorDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	public void getConnection() {
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
		// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
	}
	
	public void close() {
		try {
			if(rs != null) {
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
	
	
	public int authorInsert(AuthorVo authorVo) {
		int count = -1;
		
		try {
			getConnection();
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//준비
			String query = "";
			query += " insert into author";
			query += " values(seq_author_id.nextval, ?, ?)";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			
			//실행
			count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count + "건이 등록 되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
	}
	
	
	public int authorUpdate(AuthorVo authorVo) {
		int count = -1;
		
		try {
			getConnection();
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//준비
			String query = "";
			query += " update	author";
			query += " set		author_name = ?,";
			query += " 			author_desc = ?";
			query += " where	author_id = ?";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());
			
			//실행
			count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count + "건이 업데이트 되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
	}
	
	
	public int authorDelete(int authorId) {
		int count = -1;
		
		try {
			getConnection();
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//준비
			String query = "";
			query += "  delete from author";
			query += " where author_id = ?";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			
			//실행
			count = pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count + "건이 삭제 되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
	}
	
	
	public List<AuthorVo> authorSelect() {
		
		List<AuthorVo> authorList = new ArrayList<>();
		
		try {
			getConnection();
			
		// 3. SQL문 준비 / 바인딩 / 실행
			//준비
			String query = "";
			query += "  select	author_id,";
			query += "  		author_name,";
			query += "  		author_desc";
			query += " from		author";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
		// 4.결과처리
			//리스트로 만들기
			
			while(rs.next()) {
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorList.add(authorVo);
			}
			
			//리스트 출력해보기
			//System.out.println(authorList.toString());
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return authorList;
	}
	
	
	
}

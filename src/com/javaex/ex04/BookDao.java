package com.javaex.ex04;

import java.sql.*;
import java.util.*;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	
	private void getConnection() {
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
	
	private void close() {
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
	
	
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " insert into book";
			query += " values(seq_book_id.nextval, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 등록되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();
		
		return count;
	}
	
	
	public int bookUpdate(String title, String pubs, String pubDate, int authorId, int bookId) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " update	book";
			query += " set title = ?,";
			query += " 	   pubs = ?,";
			query += "     pub_date = ?,";
			query += "     author_id = ?";
			query += " where	book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 업데이트되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return count;
	}
	
	
	public int bookDelete(int bookId) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " delete from book";
			query += " where book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return count;
	}
	
	
	public List<BookVo> bookSelect() {
		
		List<BookVo> bookList = new ArrayList<>();
		
		try {
			getConnection();
			
			String query = "";
			
			query += " select	book_id,";
			query += " 			title,";
			query += " 			pubs,";
			query += " 			to_char(pub_date, ?),";
			query += " 			author_name";
			query += " from book b left outer join author a";
			query += " on b.author_id = a.author_id";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "YY/MM/DD");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				String pubDate = rs.getString(4);
				String authorName = rs.getString(5);
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorName);
				
				bookList.add(bookVo);
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return bookList;
	}
	
	
	public List<FullVo> bookSelectAll() {
		
		List<FullVo> fullList = new ArrayList<>();
		
		try {
			getConnection();
			
			String query = "";
			
			query += " select	book_id,";
			query += " 			title,";
			query += " 			pubs,";
			query += " 			to_char(pub_date, ?),";
			query += " 			b.author_id,";
			query += " 			author_name,";
			query += " 			author_desc";
			query += " from book b left outer join author a";
			query += " on b.author_id = a.author_id";
			query += " order by book_id";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "YY/MM/DD");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				String pubDate = rs.getString(4);
				int authorId = rs.getInt(5);
				String authorName = rs.getString(6);
				String authorDesc = rs.getString(7);
				
				FullVo fullVo = new FullVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				
				fullList.add(fullVo);
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return fullList;
	}
}

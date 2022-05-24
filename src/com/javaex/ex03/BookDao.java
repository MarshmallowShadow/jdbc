package com.javaex.ex03;
import java.sql.*;
import java.util.*;

public class BookDao {
	
	
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "";
			query += " insert into book";
			query += " values(seq_book_id.nextvalue, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 추가되었습니다.");
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
		return count;
	}
	
	public int bookUpdate(String title, String pubs, String pubDate, int authorId, int bookId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "";
			query += " update	books";
			query += " set		title = ?,";
			query += " 			pubs = ?,";
			query += " 			pub_date = ?,";
			query += " 			author_id = ?";
			query += " where	book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 업데이트되었습니다.");
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
		return count;
	}
	
	public int bookDelete(int bookId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "";
			query += " delete from books";
			query += " where book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
		return count;
	}
	
	public List<BookVo> bookSelect(String search) {
		List<BookVo> bookList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String query = "";
			query += " select	book_id,";
			query += " 			title,";
			query += " 			pubs,";
			query += " 			to_char(pub_date, ?),";
			query += " 			author_name";
			query += " from book b left outer join author a";
			query += " on b.author_id = a.author_id";
			query += " order by book_id";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "YYYY-MM-DD");
			
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
			
			int i = 0;
			
			while(i<bookList.size()) {
				boolean contains = false;
				
				if(Integer.toString(bookList.get(i).getBookId()).contains(search)) {
					contains = true;
				}
				if(bookList.get(i).getTitle().contains(search)) {
					contains = true;
				}
				if(bookList.get(i).getPubs().contains(search)) {
					contains = true;
				}
				if(bookList.get(i).getPubDate().contains(search)) {
					contains = true;
				}
				if(bookList.get(i).getAuthorName().contains(search)) {
					contains = true;
				}
				
				if(!contains) {
					bookList.remove(i);
				} else {
					i++;
				}
			}
			
			
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(rs != null) {
					rs.close();
				}
				if(sc != null) {
					sc.close();
				}
			} catch(SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
		return bookList;
	}
}

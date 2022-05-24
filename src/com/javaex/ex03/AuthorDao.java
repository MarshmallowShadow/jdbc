package com.javaex.ex03;
import java.sql.*;
import java.util.*;

public class AuthorDao {
	
	
	public static int authorInsert(String authorName, String authorDesc) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "wedb", "webdb");
			
			String query = "";
			query += " insert into author";
			query += " values(seq.author_id.nextvalue, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			
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
	
	public static int authorUpdate(String authorName, String authorDesc, int authorId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "wedb", "webdb");
			
			String query = "";
			query += " update	author";
			query += " set		author_name = ?,";
			query += " 			author_desc = ?";
			query += " where	author_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			pstmt.setInt(3, authorId);
			
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
	
	public static int authorDelete(int authorId) {
		int count = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "wedb", "webdb");
			
			String query = "";
			query += " delete from author";
			query += " where author_id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			
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
	
	public List<AuthorVo> authorSelect(){
		List<AuthorVo> authorList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "wedb", "webdb");
			
			String query = "";
			query += " select	author_id,";
			query += " 			author_name";
			query += " 			author_desc";
			query += " from		author";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);
				
				AuthorVo authorVo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorList.add(authorVo);
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
			} catch(SQLException e) {
				System.out.println("error: " + e);
			}
		}
		
		return authorList;
	}
}

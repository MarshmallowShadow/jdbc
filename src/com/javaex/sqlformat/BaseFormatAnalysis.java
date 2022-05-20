package com.javaex.sqlformat;
import java.sql.*;

public class BaseFormatAnalysis {
	public static void main(String[] args) {
		//0. import java.sql.*;
		
		//변수 선언
		Connection conn = null; //연결할때 사용
		PreparedStatement pstmt = null; //query문을 실행할때 필수
		//ResultSet rs = null; //select에 이용 (DB용 배열?)
		
		try {
		//1. JDBC 드라이버 (Oracle) 로딩
			//해당 데이터베이스 드라이버를 로드 (스태틱에서 등록?)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2. Connection 얻어오기
			//SQL url 형식 (jdbc:oracle:드라이버종류:@호스트이름:포트:SID(게이터베이스 속성에 있음))
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			
			//로그인 (cmd의 "sqlplus webdb/webdb"랑 똑같음?
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			
		//3. SQL문 준비 / 바인딩 / 실행
			//SQL 문 준비
			String query = "";
			
			query += " insert into author";
			query += " values(seq_auth.nextval, ?, ?)";
			
			//바인딩
			pstmt = conn.prepareStatement(query); //쿼리를 문자열로 만들기;
			pstmt.setString(1, "기안84"); // ? #1 (순서 중요)
			pstmt.setString(2, "기안동에서 산 84년생"); // ? #2
			
			//실행 (자동 commit 주의)
			//query문 실행 (insert, update, delete)
			int count = pstmt.executeUpdate();
			
			//query문 실행 (select)
			//rs = pstmt.executeQuery();
			
			
		//4.결과처리
			//insert, update, delete
			System.out.println(count + " 행 이(가) 삽입되었습니다.");
			
			//select
			/*
			while(rs.next()) {
				int authorId = rs.getInt(1); //컬럼 시작 자리는 항상 1 (author_id)
				String authorName = rs.getString("author_name"); //컬럼명도 사용 가능
				String authorDesc = rs.getString("desc"); //alias있으면 alias로 지정 필수
				
				System.out.println(authorId + ", " + authorName + ", " + authorDesc);
			}
			*/
		} catch (ClassNotFoundException e) { //<1. JDBC 드라이버 (Oracle) 로딩> 단계에서 에외
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) { //SQL(query)코드 오류 및 <2. Connection 얻어오기> 단계에서 발생
			System.out.println("error:" + e);
		} finally {
			//5. 자원정리
			try {
				/*
				if (rs != null) {
					rs.close();
				}
				*/
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
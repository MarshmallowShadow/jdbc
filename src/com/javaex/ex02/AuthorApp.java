package com.javaex.ex02;

public class AuthorApp {
	public static void main(String[] args) {
		AuthorDao authorDao = new AuthorDao();
		
		/*
		authorDao.authorInsert("이문열","경북영양");
		authorDao.authorInsert("박경리","경상남도 통영");
		authorDao.authorInsert("유시민","17대 국회의원");
		authorDao.authorInsert("기안84","기안동에서 산 84년생");
		authorDao.authorInsert("강풀","온라인 만화가 1세대");
		authorDao.authorInsert("김영하","알쓸신잡");
		*/
		
		authorDao.authorSelect();
	}
}

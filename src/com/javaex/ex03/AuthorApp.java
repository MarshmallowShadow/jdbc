package com.javaex.ex03;

import java.util.List;

import com.javaex.ex02.AuthorDao;
import com.javaex.ex02.AuthorVo;

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
		
		//authorDao.authorUpdate("이문열", "서울특별시", 1);
		
		//authorDao.authorDelete(3);
		
		List<AuthorVo> authorList =  authorDao.authorSelect();
		
		for(int i = 0; i<authorList.size(); i++) {
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId + ", " + authorName + ", " + authorDesc);
		}
	}
}

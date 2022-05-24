package com.javaex.ex03;

import java.util.List;
import java.util.Scanner;

public class BookApp {
	public static void main(String[] args) {
		BookDao bookDao = new BookDao();
		
		/*
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert("토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookInsert("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDao.bookInsert("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDao.bookInsert("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert("26년", "재미주의", "2012-02-04", 5);
		*/
		
		//bookDao.bookUpdate("1984", "Big Brother Books", "1984-04-29", 1, 2);
		
		//bookDao.bookDelete(6);
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("검색어를 입력하세요 (종료는 q): ");
			
			String search = sc.nextLine();
			
			if(search.equals("q")) {
				break;
			}
			
			List<BookVo> bookList = bookDao.bookSelect(search);
			
			if(bookList.size() == 0) {
				System.out.println("검색결과가 없습니다");
			} else {
				for(int i=0; i<bookList.size(); i++) {
					int bookId = bookList.get(i).getBookId();
					String title = bookList.get(i).getTitle();
					String pubs = bookList.get(i).getPubs();
					String pubDate = bookList.get(i).getPubDate();
					String authorName = bookList.get(i).getAuthorName();
					
					System.out.println(bookId + ", " + title + ", " + pubs + ", " + pubDate + ", " + authorName);
				}
			}
		}
		
		sc.close();
	}
}

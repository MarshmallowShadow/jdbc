package com.javaex.ex03;

public class BookVo {
	private int bookId;
	private String title;
	private String pubs;
	private String pubDate;
	private String authorName;
	
	public BookVo() {
		super();
	}
	public BookVo(int bookId, String title, String pubs, String pubDate, String authorName) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorName = authorName;
	}
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "BookVo[book_id=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate + ", authorName=" + authorName + "]";
	}
}

package com.javaex.ex04;

public class AuthorVo {
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	public AuthorVo() {
		super();
	}
	public AuthorVo(String authorName, String authorDesc) {
		this();
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	public AuthorVo(int authorId, String authorName, String authorDesc) {
		this(authorName, authorDesc);
		this.authorId = authorId;
	}
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
	@Override
	public String toString() {
		return "AuthorVo[authorId=" + authorId + ", authorName=" + authorName + ", authorDesc=" + authorDesc + "]";
	}
}

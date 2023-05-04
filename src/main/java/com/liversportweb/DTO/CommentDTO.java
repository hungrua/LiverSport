package com.liversportweb.DTO;

import java.sql.Date;

public class CommentDTO {

	private Long id;
	private String content;
	private Date createDate;
	private String avatar;
	private String userName;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * {
	 * 	data-id = data.id
	 * (this).data("id")
	 * 
	 * 	"id" : id,
	 * 	"content": content
	 * }
	 * */

}
package com.tewrrss.dto;

import java.io.Serializable;
import java.sql.Date;

public class Post implements Serializable, DTO {
	private static final long serialVersionUID = 99834L;

	private String content;
	private Date creationDate;
	private String userEmail;
	private String communityName;

	public Post() { }

	public Post(String content, String creationDate, String userEmail, String communityName) {
		this.setContent(content);
		this.setCreationDate(Date.valueOf(creationDate));
		this.setUserEmail(userEmail);
		this.setCommunityName(communityName);
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
}

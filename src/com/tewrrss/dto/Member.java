package com.tewrrss.dto;

import java.io.Serializable;

public class Member implements Serializable, DTO {
	private static final long serialVersionUID = 99834L;

	private String userEmail;
	private String communityName;

	public Member() { }

	public Member(String userEmail, String communityName) {
		this.setUserEmail(userEmail);
		this.setCommunityName(communityName);
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

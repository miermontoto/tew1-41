package com.tewrrss.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 56556L;

	private String login;
	private String name;
	private int role;

	public User(String login, String name) {
		this.setLogin(login);
		this.setName(name);
		this.setRole(Role.USER);
	}

	public User(String login, String name, int role) {
		this.setLogin(login);
		this.setName(name);
		this.setRole(role);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		if (role != Role.ADMIN && role != Role.USER)
			throw new IllegalArgumentException(String.format("Invalid role: %d", role));

		this.role = role;
	}
}

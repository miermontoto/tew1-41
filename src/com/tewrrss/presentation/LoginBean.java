package com.tewrrss.presentation;

public class LoginBean {

	private String username;
	private String password;

	public LoginBean() {
		this.username = "";
		this.password = "";
	}

	public String manageLogin() {
		// TODO: Lógica que haga una consulta a BBDD. Implementar en otr Bean
		boolean authStatus = true;

		if(!authStatus) {
			return "error";
		}

		return "success";
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

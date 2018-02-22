package com.securde.papema.entity;

public class Account {
	
	private String accountType;
	private String username;
	private String password;
	
	public Account() {
		accountType = "";
		username = "";
		password = "";
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}

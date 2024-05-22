package com.api.pojo;

public class AuthorizationPojo {
	private String username;
	private String password;

	public AuthorizationPojo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "GenerateAuthorizationPojo [username=" + username + ", password=" + password + "]";
	}

}

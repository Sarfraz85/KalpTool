package com.kalp.tool.model;



public class LoginReply {
	private String userName;
	private String jwt;
	public LoginReply() {
	}
	public LoginReply(String userName, String jwt) {
		super();
		this.userName = userName;
		this.jwt = jwt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public boolean isEmpty() {
		if(this.getUserName()==null || this.getUserName().isEmpty())
			return true;
		return false;
	}
	
}

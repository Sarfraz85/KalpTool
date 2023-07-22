package com.kalp.tool.util;

public class jwtToken {
	private String token;
	
	public jwtToken() {}
	public jwtToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

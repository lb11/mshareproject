package com.group.utils;

import com.group.internet.InternetPaser;

public class MessageType {
	
	private int state;
	
	private String username;
	private String password;
	
	
	
	
	
	public MessageType(int state, String username, String password) {
		this.state = state;
		this.username = username;
		this.password = password;
	}




	public int getState() {
		return state;
	}




	public void setState(int state) {
		this.state = state;
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


	@Override
	public String toString() {
		return InternetPaser.PaserForSendMessafe(state, username, password);
	}
}

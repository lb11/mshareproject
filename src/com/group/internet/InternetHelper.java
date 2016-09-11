package com.group.internet;

import java.io.OutputStream;

import com.group.utils.PersonInfo;

public class InternetHelper implements InternetFace {

	@Override
	public int Login(String username, String passward) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String Register(PersonInfo info) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public int sendText(String from, String to, String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendText(String from, String to, OutputStream out) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendText(String to, String text) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendFile(String to, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendFile(String from, String to, String path) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sendFile(String to, OutputStream out) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PersonInfo getPersonInfo(String who) {
		// TODO Auto-generated method stub
		return null;
	}

}

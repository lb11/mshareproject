package com.group.internet;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.group.utils.M;
import com.group.utils.MessageInfo;

/**
 * 消息的解析器
 */
public class InternetPaser {
	private ArrayList<String> users = new ArrayList<String>();
	private ArrayList<String> groups = new ArrayList<String>();

	/**
	 * limit the message for sending style;
	 * 
	 * @param from
	 *            sender
	 * @param to
	 *            receiver
	 * @param text
	 *            message text
	 * @return the style
	 */
	public static String PaserForSendMessage(String from, String to, String text) {
		return M.Internet.INTERNETMSG + " " + to + " " + from + " " + text;
	}

	public static String PaserForSendMessafe(int type, String username,
			String password) {
		return type + " " + username + " " + password;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

	public ArrayList<String> getUsers() {
		return users;
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<String> groups) {
		this.groups = groups;
	}

	private boolean isFlsah = false;

	public boolean isFlash() {
		return isFlsah;
	}

	public ArrayList<String> getList(int index) {
		if (index == 0) {
			return getGroups();
		} else {
			return getUsers();
		}
	}

	private MessageInfo info = new MessageInfo();

	public MessageInfo getMessageInfo() {
		return info;
	}
	
	private boolean isText;
	private boolean isSuccess;

	/**
	 * 进行解析
	 * 
	 * @param text
	 */
	public void Paser(String text) {
		int index = text.indexOf(" ");
		int flag = Integer.parseInt(text.substring(0, index).trim());
		text = text.substring(index).trim();
		isText = true;
		switch (flag) {
		case M.Internet.INTERNETLOGIN:
			isText = false;
			isSuccess = text.equals("success");
			break;
		case M.Internet.INTERNETREGISTER:
			index = text.indexOf(" ");
			info.setFrom(text.substring(0, index).trim());
			info.setText(text.substring(index + 1).trim());
			break;
		case M.Internet.INTERNETMSG:
			index = text.indexOf(" ");
			info.setFrom(text.substring(0, index).trim());
			text = text.substring(index + 1).trim();
			index = text.indexOf(" ");
//			info.setDate(new Date(Long.parseLong(text.substring(0, index)
//					.trim())));
			info.setText(text.substring(index + 1));
			break;
		case M.Internet.INTERNETGETALLUSER:
			text = text.replaceAll(",", " ");
			text = text.replace("[", "");
			text = text.replace("]", "");
			info.setText(text);
			info.setFrom(null);
			break;
//		case M.Internet.:
//			isText = false;
//			isSuccess = false;
//			break;
		default:
			break;
		}
	}

}

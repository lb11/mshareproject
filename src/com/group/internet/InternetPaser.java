package com.group.internet;

import java.util.ArrayList;
import java.util.Scanner;

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
		return to + " " + from + " " + text;
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

	/**
	 * 进行解析
	 * 
	 * @param text
	 */
	public void Paser(String text) {
		if (text.contains("reflush:")) {
			isFlsah = true;
			int index = text.indexOf('[');
			text = text.substring(index);
			index = text.indexOf(']');
			String groups = text.substring(1, index);
			String users = text.substring(index + 2, text.length() - 1);

			groups = groups.replace(",", "");
			users = users.replace(",", "");

			Scanner scanner = new Scanner(groups);
			while (scanner.hasNext()) {
				this.groups.add(scanner.next());
			}
			scanner.close();

			scanner = new Scanner(users);
			while (scanner.hasNext()) {
				this.users.add(scanner.next());
			}
			scanner.close();

		} else {
			Scanner scanner = new Scanner(text);
			String flag = scanner.next().trim();
			info.clear();
			info.setGroup(flag);
			info.setFrom(scanner.next());
			info.setText(scanner.next());
			scanner.close();
		}
	}

}

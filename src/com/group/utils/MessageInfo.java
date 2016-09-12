package com.group.utils;

import com.group.internet.InternetPaser;


/**
 * 存储消息对象。
 * 
 * @author Administrator
 * 
 */
public class MessageInfo {

	private String text;// 消息的内容
	private String from;// 消息来自方的号码
	private String group; // 消息所在的分组，若为0 表示不是一个人分组的消息。
	private String to;// 给谁的消息。

	private boolean isGroupInfo = true; // 是否是一个分组的消息。

	public MessageInfo() {
		// TODO Auto-generated constructor stub
	}

	public MessageInfo(String text, String from, String to) {
		this.text = text;
		this.from = from;
		this.to = to;
	}

	public void clear() {
		text = "";
		from = "";
		group = "";
		to = "";
		isGroupInfo = true;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public boolean isGroupInfo() {
		return isGroupInfo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getGroup() {
		return group;
	}

	/**
	 * 若数值为0，表示是发送给一个人的消息
	 * 
	 * @param group
	 */
	public void setGroup(String group) {
		this.group = group;

		isGroupInfo = !group.equals("0");
	}

	/**
	 * 
	 * @return 谁接受，来自谁的消息，消息。
	 */
	public String toString() {
		return InternetPaser.PaserForSendMessage(from, to, text);
	}
}

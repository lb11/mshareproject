package com.group.utils;

import com.group.internet.InternetPaser;


/**
 * �洢��Ϣ����
 * 
 * @author Administrator
 * 
 */
public class MessageInfo {

	private String text;// ��Ϣ������
	private String from;// ��Ϣ���Է��ĺ���
	private String group; // ��Ϣ���ڵķ��飬��Ϊ0 ��ʾ����һ���˷������Ϣ��
	private String to;// ��˭����Ϣ��

	private boolean isGroupInfo = true; // �Ƿ���һ���������Ϣ��

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
	 * ����ֵΪ0����ʾ�Ƿ��͸�һ���˵���Ϣ
	 * 
	 * @param group
	 */
	public void setGroup(String group) {
		this.group = group;

		isGroupInfo = !group.equals("0");
	}

	/**
	 * 
	 * @return ˭���ܣ�����˭����Ϣ����Ϣ��
	 */
	public String toString() {
		return InternetPaser.PaserForSendMessage(from, to, text);
	}
}

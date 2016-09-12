package com.group.utils;

/**
 * 定义一些全部的消息的控制变量。
 * 
 * @author Administrator
 * 
 */
public class M {
	
	public static String selfNum = "110";
	/**
	 * 网络相关的消息变量。
	 * 
	 */
	public static class Internet {
		public static final int INTERNETMESSAGE = 0x110;
		
		
		public static final int INTERNETREGISTER = 0;
		public static final int INTERNETLOGIN = 1;
		public static final int INTERNETMSG = 2;
		public static final int INTERNETGETALLUSER = 3;
	}

	public static class ActivityResult {
		public static final int RESULTCHATTRING = 0x210;
	}
}

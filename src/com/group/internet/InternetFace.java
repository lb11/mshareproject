package com.group.internet;

import java.io.OutputStream;

import com.group.utils.PersonInfo;

/**
 * 网络端的一些接口
 * @author Administrator
 *
 */
public interface InternetFace {
	
	/**
	 * 登陆
	 * @param username
	 * @param passward
	 * @return 1 为成功，0为失败
	 */
	public int Login(String username,String passward);
	
	/**
	 * 注册
	 * @param info
	 * @return
	 */
	public String Register(PersonInfo info);
	
	
	/**
	 *发送文本消息
	 * @param from 发送方
	 * @param to	接收方
	 * @param text	文本
	 * @return 1 为成功，0为失败
	 */
	public int sendText(String from,String to,String text);
	
	public int sendText(String from,String to,OutputStream out);
	
	/**
	 *发送文本消息
	 * @param to	接收方
	 * @param text	文本
	 * @return  1 为成功，0为失败
	 */
	public int sendText(String to,String text);
	
	/**
	 * 发送文件
	 * @param to
	 * @param path
	 * @return
	 */
	public int sendFile(String to,String path);
	
	public int sendFile(String from ,String to,String path);
	
	public int sendFile(String to,OutputStream out);
	
	/**
	 * 
	 * @param who 
	 * @return 返回个人信息
	 */
	public PersonInfo getPersonInfo(String who);
	
	
	
}

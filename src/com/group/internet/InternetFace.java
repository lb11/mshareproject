package com.group.internet;

import java.io.OutputStream;


/**
 * ����˵�һЩ�ӿ�
 * @author Administrator
 *
 */
public interface InternetFace {
	
	/**
	 * ��½
	 * @param username
	 * @param passward
	 */
	public void Login(String username,String passward);
	
	
	/**
	 *�����ı���Ϣ
	 * @param from ���ͷ�
	 * @param to	���շ�
	 * @param text	�ı�
	 */
	public void sendText(String from,String to,String text);
	
	public int sendText(String from,String to,OutputStream out);
	
	/**
	 *�����ı���Ϣ
	 * @param to	���շ�
	 * @param text	�ı�
	 * @return  1 Ϊ�ɹ���0Ϊʧ��
	 */
	public int sendText(String to,String text);
	
	/**
	 * �����ļ�
	 * @param to
	 * @param path
	 * @return
	 */
	public int sendFile(String to,String path);
	
	public int sendFile(String from ,String to,String path);
	
	public int sendFile(String to,OutputStream out);
	
}

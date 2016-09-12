package com.group.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.group.utils.M;
import com.group.utils.MessageInfo;
import com.group.utils.MessageType;

public class InternetManager {

	private String host = "192.168.155.1"; // Ҫ���ӵķ����IP��ַ
	private int port = 8912; // Ҫ���ӵķ���˶�Ӧ�ļ����˿�;
	private Socket mSocketClient;

	private Handler hand;

	public InternetManager(Handler hand) {
		this.hand = hand;
	}

	private String message = null;

	/**
	 * �������ӡ�
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void Connect() throws UnknownHostException, IOException {
		mSocketClient = new Socket(host, port);
		new ReceiveThread(mSocketClient).start();

		PrintStream ps = new PrintStream(mSocketClient.getOutputStream());
		String line = null;
		while (true) {
			if (mSocketClient.isClosed() || !mSocketClient.isConnected())
				mSocketClient = new Socket(host, port);

			while (mSocketClient.isConnected() && (line = message) != null) {
				message = null; // ��շ��͵���Ϣ��
				ps.println(line);
				ps.flush();
			}
		}
	}
	
	/**
	 * ע��
	 */
	public void sendMessageToRegister(String psward){
		message = M.Internet.INTERNETREGISTER + " "+null+" "+psward+ "__ben";
	}
	
	public void sendMessageToLogin(){
		
	}

	public void sendMessage(MessageInfo msg) {
		message = msg.toString() + "__ben";
	}

	public void sendMessage(MessageType msg) {
		message = msg.toString() + "__ben";
	}

	/**
	 * ���ϵļ������Է���������Ϣ��
	 * 
	 */
	protected class ReceiveThread extends Thread {
		private Socket msocket = null;
		private BufferedReader buffer;

		public ReceiveThread(Socket socket) throws IOException {
			msocket = socket;
			buffer = new BufferedReader(new InputStreamReader(
					msocket.getInputStream()));
		}

		@Override
		public void run() {
			super.run();
			// read message;
			String line = null;

			try {
				while ((line = buffer.readLine()) != null) {
					Bundle blund = new Bundle();
					blund.putString("msg", line);

					Message msg = new Message();
					msg.what = M.Internet.INTERNETMESSAGE;
					msg.setData(blund);
					hand.sendMessage(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}

package com.group.services;

import java.io.IOException;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.group.internet.InternetManager;
import com.group.internet.InternetPaser;
import com.group.utils.M;
import com.group.utils.MessageInfo;

public class InternetService extends Service {

	private InternetPaser mpaser;// 网络资源解析。
	private InternetManager min = null;

	public interface MessageListener {
		public void OnFlashListener(int index, InternetPaser paser);

		public void OnMessageListener(MessageInfo info);
	}

	private MessageListener mMListener = null;
	

	public void setOnMessageListener(MessageListener listener) {
		mMListener = listener;
	}

	@SuppressLint("HandlerLeak")
	private Handler hand = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == M.Internet.INTERNETMESSAGE) {
				String str = msg.getData().getString("msg"); // 获取消息
				mpaser.Paser(str); // 解析消息。
				if (mMListener != null && mpaser.isFlash()) {
					mMListener.OnFlashListener(0, mpaser);
				} else {
					// 发送的消息。
					mMListener.OnMessageListener(mpaser.getMessageInfo());
				}
			}
		};
	};

	public Handler getHand() {
		return hand;
	}

	private ServiceThread mThread;

	class ServiceThread extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				min.Connect();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public class MyBinder extends Binder {

		public InternetService getService() {
			return InternetService.this;
		}
	}

	private final IBinder binder = new MyBinder();

	public void onCreate() {
		mpaser = new InternetPaser();
		min = new InternetManager(hand);

		mThread = new ServiceThread();
		mThread.start();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	public void sendToRegister(String passward){
		min.sendMessageToRegister(passward);
	}

	public void sendMessage(String from, String to, String text) {
		MessageInfo msInfo = new MessageInfo(text, from, to);
		min.sendMessage(msInfo);
	}

}

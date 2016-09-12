package com.group.chat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.group.adapters.ChatListAdapter;
import com.group.internet.InternetPaser;
import com.group.services.InternetService;
import com.group.services.InternetService.MessageListener;
import com.group.services.InternetService.MyBinder;
import com.group.utils.M;
import com.group.utils.MessageInfo;
import com.group.utils.TopBar;
import com.group.utils.TopBar.OnBarClickListener;

public class Chatting extends Activity implements OnClickListener {
	private TopBar mTopbar;
	private ListView mListView;
	private EditText mEdit;
	private Button mSend;
	
//	private MListAdapter mAdapter;
	private ArrayList<MessageInfo> mLists;
	private ChatListAdapter mAdapter;

	private String title;

	private InternetService mIntentService = null;

	private ServiceConnection intentServiceCon = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mIntentService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIntentService = ((MyBinder) service).getService();

			mIntentService.setOnMessageListener(new MessageListener() {

				@Override
				public void OnMessageListener(MessageInfo info) {
//					if (info.getFrom() == null) {
//						ShowFriendList(info.getText());
//					} else {
//						mDataBase.add(info); // 将数据先存到数据库里面。
						AddData(info);
//					}
				}

				@Override
				public void OnFlashListener(int index, InternetPaser paser) {

				}

			});
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_frame);

		title = getIntent().getStringExtra("title");
		initial();
		
		Intent intent = new Intent(Chatting.this, InternetService.class);
		startService(intent);

		bindService(intent, intentServiceCon, BIND_AUTO_CREATE);
		mListView.setSelection(mLists.size() - 1);
	}

	/**
	 * 初始化控件。
	 */
	private void initial() {

		mTopbar = (TopBar) findViewById(R.id.chatting_topbar);
		mListView = (ListView) findViewById(R.id.chat_frame_listview);
		mEdit = (EditText) findViewById(R.id.chatting_textedit);
		mSend = (Button) findViewById(R.id.chatting_send);

		mLists = new ArrayList<MessageInfo>();
		mAdapter = new ChatListAdapter(getApplicationContext(), mLists);
		mListView.setAdapter(mAdapter);
		
		mTopbar.setTitle(title);

		mTopbar.setOnBarClickListener(new OnBarClickListener() {

			@Override
			public void backClickListener() {
				Chatting.this.finish();
			}
		});

		mSend.setTextColor(Color.GRAY);
		mSend.setClickable(false);

		mEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().equals("")) {
					mSend.setTextColor(Color.GRAY);
					mSend.setClickable(false);
				} else {
					mSend.setClickable(true);
					mSend.setTextColor(Color.BLACK);
				}
			}
		});

		mSend.setOnClickListener(this);
	}
	
	
	/**
	 * 从数据库里面获取数据资源。
	 */
	private void addDatas() {
		mLists.clear();
//		List<MessageInfo> list = mDataBase.quere();
//		for (MessageInfo info : list) {
//			mLists.add(info);
//		}
	}

	private void AddData(MessageInfo info) {
		mLists.add(info);
		mAdapter.notifyDataSetChanged();
		mListView.setSelection(mLists.size() - 1);
	}

	@Override
	public void onClick(View v) {
		String msg = mEdit.getText().toString().trim();

		// 进行发送
		Toast.makeText(getApplicationContext(), msg, 1000).show();
		String to = "111";// get it from database;
		mIntentService.sendMessage(M.selfNum, to, msg);

	}

}

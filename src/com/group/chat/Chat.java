package com.group.chat;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.group.fragments.ChatRoom_Fragment;
import com.group.fragments.Friends_Fragment;
import com.group.fragments.ListData_Fragment;
import com.group.internet.InternetPaser;
import com.group.services.InternetService;
import com.group.services.InternetService.MessageListener;
import com.group.services.InternetService.MyBinder;
import com.group.utils.MessageInfo;
import com.group.utils.TopBar;

public class Chat extends FragmentActivity implements OnClickListener {
	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;
	private ArrayList<ListData_Fragment> mlists;
	private ArrayList<TextView> mlistviews;

	private int[] titles = { R.string.chat_room, R.string.chat_friend };

	private TopBar mTopbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		initial();
		setWhich(0);
	}

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

				}

				@Override
				public void OnFlashListener(int index, InternetPaser paser) {
					mlists.get(index).AddDatas(paser.getList(index));
				}

			});
		}
	};

	/**
	 * ≥ı ºªØ
	 */
	@SuppressLint("InlinedApi")
	private void initial() {

		Intent bindIntent = new Intent(Chat.this, InternetService.class);
		bindService(bindIntent, intentServiceCon, Context.BIND_IMPORTANT);
		startService(bindIntent);

		mFragmentManager = getSupportFragmentManager();

		mTopbar = (TopBar) findViewById(R.id.activity_topbar);
		mTopbar.setBackVisibility(View.GONE);

		mlists = new ArrayList<ListData_Fragment>();
		mlists.add(null);
		mlists.add(null);

		mlistviews = new ArrayList<TextView>();
		mlistviews.add((TextView) findViewById(R.id.text_chat_friends));
		mlistviews.add((TextView) findViewById(R.id.text_chat_room));

		for (TextView text : mlistviews) {
			text.setOnClickListener(this);
		}
	}

	private void setTheFrame(int index) {
		mFragmentTransaction = mFragmentManager.beginTransaction();

		HideViews();

		ListData_Fragment mf = mlists.get(index);
		if (mf == null) {
			if (index == 0) {
				mf = new ChatRoom_Fragment();
			} else {
				mf = new Friends_Fragment(this);
			}
			mlists.set(index, mf);
			mFragmentTransaction.add(R.id.activity_fragment_layout, mf);
		}

		mFragmentTransaction.show(mf);

		mFragmentTransaction.commit();
	}

	private void HideViews() {
		try {
			for (Fragment fm : mlists) {
				if (fm != null)
					mFragmentTransaction.hide(fm);
			}
		} catch (Exception e) {

		}
	}

	private void setText(int index) {
		mTopbar.setTitle(getResources().getString(titles[index]));
	}

	private void setWhich(int index) {
		setTheFrame(index);
		setText(index);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_chat_room:
			setWhich(0);
			break;
		case R.id.text_chat_friends:
			setWhich(1);
			break;

		default:
			break;
		}
	}
}

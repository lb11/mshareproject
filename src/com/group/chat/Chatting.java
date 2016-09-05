package com.group.chat;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.group.adapters.MListAdapter;
import com.group.utils.TopBar;


public class Chatting extends Activity implements OnClickListener {
	private TopBar mTopbar;
	private ListView chatFrame;
	private EditText mEdit;
	private Button mSend;
	private MListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_frame);

		initial();
	}

	/**
	 * ³õÊ¼»¯¿Ø¼þ¡£
	 */
	private void initial() {
		mTopbar = (TopBar) findViewById(R.id.chatting_topbar);
		chatFrame = (ListView) findViewById(R.id.chat_frame_listview);
		mEdit = (EditText) findViewById(R.id.chatting_textedit);
		mSend = (Button) findViewById(R.id.chatting_send);

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

	@Override
	public void onClick(View v) {

		String msg = mEdit.getText().toString().trim();

		if (msg.equals("") || msg == null) {

		} else {
			Toast.makeText(getApplicationContext(), msg, 1000).show();
		}
	}

}

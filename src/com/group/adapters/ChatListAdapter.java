package com.group.adapters;

import java.util.List;

import com.group.chat.R;
import com.group.utils.M;
import com.group.utils.MessageInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatListAdapter extends BaseAdapter {
	private List<MessageInfo> mLists;
	private LayoutInflater inflater;

	public ChatListAdapter(Context context, List<MessageInfo> lists) {
		super();
		mLists = lists;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mLists.size();
	}

	@Override
	public Object getItem(int position) {
		return mLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder mHolder = null;
		MessageInfo item = (MessageInfo) getItem(position);
		boolean isNew = true;
		if (convertView != null) {
			mHolder = (viewHolder) convertView.getTag();
			if (position == mHolder.index) {
				isNew = false;
			}
		}

		if (isNew) {
			mHolder = new viewHolder();
			if (item.getFrom().equals(M.selfNum)) {
				convertView = inflater.inflate(R.layout.chat_item_2, parent,
						false);
				mHolder.text_who = (TextView) convertView
						.findViewById(R.id.chat_item2_who);
				mHolder.text_text = (TextView) convertView
						.findViewById(R.id.chat_item2_text);
			} else {
				convertView = inflater.inflate(R.layout.chat_item_1, parent,
						false);
				mHolder.text_who = (TextView) convertView
						.findViewById(R.id.chat_item1_who);
				mHolder.text_text = (TextView) convertView
						.findViewById(R.id.chat_item1_text);
			}
			mHolder.index = position;
			convertView.setTag(mHolder);
		}

		mHolder.text_who.setText(item.getFrom());
		mHolder.text_text.setText(item.getText());
		return convertView;
	}

	protected class viewHolder {
		int index;
		TextView text_who, text_text;
	}

}

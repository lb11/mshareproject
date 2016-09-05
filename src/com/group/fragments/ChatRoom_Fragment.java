package com.group.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.group.adapters.MListAdapter;
import com.group.chat.R;


public class ChatRoom_Fragment extends ListData_Fragment {
	private ListView mView;

	private MListAdapter adapter;
	private ArrayList<String> lists;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = (ListView) inflater.inflate(R.layout.list_frame, container,
				false);

		initial();
		return mView;
	}

	@Override
	public void AddDatas(List<String> lists) {
		for (String str : lists) {
			this.lists.add(str);
		}
		adapter.notifyDataSetChanged();
	}

	private void initial() {
		lists = new ArrayList<String>();
		adapter = new MListAdapter(lists, getActivity());
		mView.setAdapter(adapter);
	}

}

package com.group.fragments;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.group.adapters.ExpendListViewAdapter;
import com.group.chat.Chatting;
import com.group.chat.R;
import com.group.utils.M;
import com.group.utils.MyListData;


public class Friends_Fragment extends ListData_Fragment implements
		OnItemClickListener {
	private ListView mView;
	private ExpendListViewAdapter mListAdapter;
	private MyListData mMyListData;

	private Context mContext;

	public Friends_Fragment(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = (ListView) inflater.inflate(R.layout.list_frame, container,
				false);
		initViews();

		return mView;
	}

	private void initViews() {
		mMyListData = new MyListData();
		mMyListData.add("我的好友");
		mMyListData.add("同学");
		mMyListData.add("王五", 0);
		mMyListData.add("赵四", 1);

		mListAdapter = new ExpendListViewAdapter(mContext, mMyListData);
		mView.setAdapter(mListAdapter);
		mView.setOnItemClickListener(this);
	}

	@Override
	public void AddDatas(List<String> lists) {
		// TODO Auto-generated method stub

	}

	private void LaunchChatting(String who) {
		Intent intent = new Intent(getActivity(), Chatting.class);
		intent.putExtra("title", who);
		getActivity().startActivityForResult(intent,
				M.ActivityResult.RESULTCHATTRING);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String info = mMyListData.getDatas().get(position);
		int index = mMyListData.inParentindexOf(info);
		if (index != -1) {
			// 父链表的节点
			if (mMyListData.haveChild(index)) {
				mMyListData.changeItemTypeState(index);
				mListAdapter.notifyDataSetChanged();
			}
		} else {
			// 子链表的节点。项地图那块发起消息，并把这个节点的数据发个对方。
			LaunchChatting(info);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == M.ActivityResult.RESULTCHATTRING) {
			// 从聊天界面返回。
			
		}
	}
}

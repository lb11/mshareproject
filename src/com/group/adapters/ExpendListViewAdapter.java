package com.group.adapters;

import com.group.chat.R;
import com.group.utils.MyListData;
import com.group.utils.MyListData.Type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpendListViewAdapter extends ArrayAdapter<String> {
	private MyListData mdatas;
	private LayoutInflater minflater;

	public ExpendListViewAdapter(Context context, MyListData objects) {
		super(context, -1);
		mdatas = objects;
		minflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoder holder = null;
		String info = mdatas.getDatas().get(position);
		if (convertView == null) {
			convertView = minflater.inflate(R.layout.treeview_item, parent,
					false);
			
			holder = new ViewHoder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.textview_item_icon);
			holder.text = (TextView) convertView
					.findViewById(R.id.textview_item_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHoder) convertView.getTag();
		}

		initalItemView(holder, info);
		holder.text.setText(info);

		return convertView;
	}

	/**
	 * 初始化Item的控件的显示状态。
	 * 
	 * @param holder
	 * @param info
	 */
	private void initalItemView(ViewHoder holder, String info) {
		int index = mdatas.inParentindexOf(info);
		// 回复上次的设置。
		holder.text.setPadding(0, 0, 0, 0);
		holder.image.setVisibility(View.VISIBLE);
		// 再根据情况来设置现在的布局情况。
		if (index != -1) {
			// 是父链表中的一个节点。
			if (mdatas.haveChild(index)) {
				// 有孩子节点
				if (Type.OPEN == mdatas.getItemType(index)) {
					// 处于展开状态。
					holder.image.setImageResource(R.drawable.tree_ex);
				} else {
					// 关闭状态。
					holder.image.setImageResource(R.drawable.tree_ec);
				}
			} else {
				// 没有孩子节点。
				holder.image.setVisibility(View.INVISIBLE);
			}
		} else {
			// 不是父链表中的节点，说明是孩子节点。
			holder.image.setVisibility(View.INVISIBLE);
			holder.text.setPadding(30, 0, 0, 0);
		}
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	public class ViewHoder {
		ImageView image;
		TextView text;
	}

	@Override
	public int getCount() {
		return mdatas.size();
	}

}

package com.group.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.group.chat.R;

public class TopBar extends RelativeLayout implements OnClickListener {

	private LayoutInflater inflater;
	private TextView back, title;
	private View view;
	private boolean isFirst = true;

	private OnBarClickListener listener;

	public TopBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TopBar(Context context) {
		this(context, null);
	}

	@SuppressLint("InflateParams")
	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.top_bar, null, false);
		initialWidget();
	}

	private void initialWidget() {
		back = (TextView) view.findViewById(R.id.top_back);
		title = (TextView) view.findViewById(R.id.top_title);

		back.setOnClickListener(this);
	}

	public void setBackVisibility(int visibility) {
		back.setVisibility(visibility);
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public interface OnBarClickListener {
		public void backClickListener();

	}

	public void setOnBarClickListener(OnBarClickListener listener) {
		this.listener = listener;
	}

	public void setTitleTextVisible(int visible) {
		title.setVisibility(visible);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (isFirst) {
			LayoutParams parms = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);
			addView(view, parms);
			isFirst = false;
		}
	}

	@Override
	public void onClick(View v) {
		if (listener != null) {
			listener.backClickListener();
		}
	}
}

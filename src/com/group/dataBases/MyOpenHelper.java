package com.group.dataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
	private static int version = 1;
	private static String name = "chat.db";

	public MyOpenHelper(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(HistoryChat.create_table_chathistory); // 创建表单；
		db.execSQL(FriendNickName.create_table_friendnickname);
		db.execSQL(Friends.create_table_friends);
		db.execSQL(GroupHistoryChat.create_table_groupchathistory);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub Ignore this change

	}
	

}

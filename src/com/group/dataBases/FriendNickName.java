package com.group.dataBases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 在每个类里都定义一些String的常量，用于作为查询操作的条件。
 * 
 * @author Administrator
 * 
 */
public class FriendNickName extends MDatas {
	private MyOpenHelper openHelper;

	private String table_name = "friendnickname";

	// 表单创建的语句。
	public static String create_table_friendnickname = "create table friendnickname("//
			+ "user varchar(20) not null,"// 用户的信息
			+ "who varchar(20) not null,"// 发送者的号码
			+ "nickname varchar(50),"// 消息的内容。
			+ "primary key(user,who)"// 主键
			+ ");";

	/**
	 * 限制条件的集合。
	 * 
	 */
	public static class HistoryChatLimits {
		public static final String WHERE_USER = "user = ? ";
		public static final String WhRER_WHO = " who = ? ";
		public static final String WhRER_MESSAGE = " nickname = ? ";

		public static final String CON_AND = "and";
	}

	private static FriendNickName mInstance = null;

	public static FriendNickName getInstance(Context context ) {
		if (mInstance == null) {
			mInstance = new FriendNickName(context);
		}

		return mInstance;
	}

	private FriendNickName(Context context ) {
		super(context);
		openHelper = new MyOpenHelper(context);
	}

	@Override
	public void add(Object[] objects) {
		SQLiteDatabase date = openHelper.getWritableDatabase();
		try {
			String sql = "insert into " + table_name
					+ "(user,who,nickname)values(?,?,?)";
			date.execSQL(sql, objects);
		} catch (Exception e) {
			Log.i("MSGERROR", "ERROR _liubenm ");
		} finally {
			if (date != null)
				date.close();
		}
	}

	@Override
	public void delete(String where, Object[] objects) {
		SQLiteDatabase date = openHelper.getWritableDatabase();
		try {
			String sql = "delete from" + table_name + " where " + where;
			date.execSQL(sql, objects);
		} catch (Exception e) {
		}
		if (date != null)
			date.close();
	}

	@Override
	public List<Map<String, String>> quere(String where, String[] objects) {
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		SQLiteDatabase date = openHelper.getReadableDatabase();
		String sql;
		if (where == null) {
			sql = "select * from " + table_name;
		} else {
			sql = "select * from " + table_name + " where " + where;
		}
		Cursor cursor = date.rawQuery(sql, objects);
		int n = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			Map<String, String> data = new HashMap<String, String>();
			for (int i = 0; i < n; i++) {
				String name = cursor.getColumnName(i);
				String value = cursor.getString(cursor.getColumnIndex(name));
				if (value == null) {
					value = "";
				}
				data.put(name, value);
			}
			lists.add(data);
		}
		if (date != null)
			date.close();
		return lists;
	}
}

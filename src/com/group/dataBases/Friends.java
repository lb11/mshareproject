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
 * ��ÿ�����ﶼ����һЩString�ĳ�����������Ϊ��ѯ������������
 * 
 * @author Administrator
 * 
 */
public class Friends extends MDatas {
	private MyOpenHelper openHelper;

	private String table_name = "friends";

	// ����������䡣
	public static String create_table_friends = "create table friends("//
			+ "user varchar(20) not null,"// �û�����Ϣ
			+ "who varchar(20) not null,"// �����ߵĺ���
			+ "primary key(user,who)"// ����
			+ ");";

	/**
	 * ���������ļ��ϡ�
	 * 
	 */
	public static class HistoryChatLimits {
		public static final String WHERE_USER = "user = ? ";
		public static final String WhRER_WHO = " who = ? ";

		public static final String CON_AND = "and";
	}

	private static Friends mInstance = null;

	public static Friends getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new Friends(context);
		}

		return mInstance;
	}

	private Friends(Context context) {
		super(context);
		openHelper = new MyOpenHelper(context);
	}

	@Override
	public void add(Object[] objects) {
		SQLiteDatabase date = openHelper.getWritableDatabase();
		try {
			String sql = "insert into " + table_name
					+ "(user,who)values(?,?)";
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

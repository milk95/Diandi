package com.example.diandi.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DiandiOpenHelper extends SQLiteOpenHelper {

	/*
	 * 数据库建表语句
	 */
	public static final String CREATE_ACCOUNTINFO = "create table AccountInfo ("
			+ "id integer primary key autoincrement, "
			+ "address text, "
			+ "password text, " + "username text)";

	public static final String CREATE_SAYDATA = "create table SayData ("
			+ "id integer primary key autoincrement, " + "nowaddress text, "
			+ "saysentence text, " + "sayedittime text)";

	public static final String CREATE_IDEADATA = "create table IdeaData ("
			+ "id integer primary key autoincrement, " + "nowaddress text, "
			+ "ideatitle text, " + "ideacontent text, " + "date text)";

	public DiandiOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	/*
	 * 建表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_ACCOUNTINFO);
		db.execSQL(CREATE_SAYDATA);
		db.execSQL(CREATE_IDEADATA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
}

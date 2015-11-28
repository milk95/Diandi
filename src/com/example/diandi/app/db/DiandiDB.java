package com.example.diandi.app.db;

import java.util.ArrayList;
import java.util.List;

import com.example.diandi.app.model.AccountInfo;
import com.example.diandi.app.model.IdeaData;
import com.example.diandi.app.model.SayData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DiandiDB {

	/*
	 * 数据库名
	 */
	public static final String DB_NAME = "diandi";

	/*
	 * 数据库版本
	 */
	public static final int VERSION = 1;

	private static DiandiDB diandiDB;
	private SQLiteDatabase db;

	/*
	 * 构造方法私有化
	 */
	private DiandiDB(Context context) {
		DiandiOpenHelper dbHelper = new DiandiOpenHelper(context, DB_NAME,
				null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/*
	 * 获取DiandiDB的实例
	 */
	public synchronized static DiandiDB getInstance(Context context) {

		if (diandiDB == null) {
			diandiDB = new DiandiDB(context);
		}
		return diandiDB;
	}

	/*
	 * 将AccountInfo存到数据库中
	 */
	public void saveAccountInfo(AccountInfo accountInfo) {
		if (accountInfo != null) {
			ContentValues values = new ContentValues();
			values.put("address", accountInfo.getAddress());
			values.put("password", accountInfo.getPassword());
			values.put("username", accountInfo.getName());
			db.insert("AccountInfo", null, values);
		}
	}

	/*
	 * 从数据库读取所有账户的信息
	 */
	public List<AccountInfo> loadAccountInfos() {
		List<AccountInfo> list = new ArrayList<AccountInfo>();
		Cursor cursor = db.query("AccountInfo", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
				accountInfo.setAddress(cursor.getString(cursor
						.getColumnIndex("address")));
				accountInfo.setPassword(cursor.getString(cursor
						.getColumnIndex("password")));
				accountInfo.setName(cursor.getString(cursor
						.getColumnIndex("username")));
				list.add(accountInfo);
			} while (cursor.moveToNext());
		}

		return list;
	}

	/*
	 * 将sayData的数据存到数据库中
	 */
	public void saveSayData(SayData sayData) {
		if (sayData != null) {
			ContentValues values = new ContentValues();
			values.put("nowaddress", sayData.getNowAddress());
			values.put("saysentence", sayData.getSaySentence());
			values.put("sayedittime", sayData.getSayEditTime());
			db.insert("SayData", null, values);
		}
	}

	/*
	 * 更新sayData中的某一项数据
	 */
	public void updateSayData(SayData sayData, String sentence) {
		if (sayData != null) {
			ContentValues values = new ContentValues();
			values.put("saysentence", sayData.getSaySentence());
			db.update("SayData", values, "saysentence",
					new String[] { sentence });
		}
	}

	/*
	 * 从数据库中读取sayData的所有信息
	 */
	public List<SayData> loadSayDatas() {
		List<SayData> list = new ArrayList<SayData>();
		Cursor cursor = db.query("SayData", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				SayData sayData = new SayData();
				sayData.setId(cursor.getInt(cursor.getColumnIndex("id")));
				sayData.setSaySentence(cursor.getString(cursor
						.getColumnIndex("saysentence")));
				sayData.setNowAddress(cursor.getString(cursor
						.getColumnIndex("nowaddress")));
				sayData.setSayEditTime(cursor.getString(cursor
						.getColumnIndex("sayedittime")));
				list.add(sayData);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveIdeaData(IdeaData ideaData) {
		if (ideaData != null) {
			ContentValues values = new ContentValues();
			values.put("nowaddress", ideaData.getNowAddress());
			values.put("ideatitle", ideaData.getIdeaTitle());
			values.put("ideacontent", ideaData.getIdeaContent());
			values.put("date", ideaData.getDate());
			db.insert("IdeaData", null, values);
		}
	}

	public void updateIdeaData(IdeaData ideaData, String title) {
		if (ideaData != null) {
			ContentValues values = new ContentValues();
			values.put("ideatitle", ideaData.getIdeaTitle());
			values.put("ideacontent", ideaData.getIdeaContent());
			values.put("date", ideaData.getDate());
			db.update("IdeaData", values, "ideatitle=?", new String[] { title });
		}
	}

	public List<IdeaData> loadIdeaDatas() {
		List<IdeaData> list = new ArrayList<IdeaData>();
		Cursor cursor = db
				.query("IdeaData", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				IdeaData ideaData = new IdeaData();
				ideaData.setId(cursor.getInt(cursor.getColumnIndex("id")));
				ideaData.setNowAddress(cursor.getString(cursor
						.getColumnIndex("nowaddress")));
				ideaData.setIdeaTitle(cursor.getString(cursor
						.getColumnIndex("ideatitle")));
				ideaData.setIdeaContent(cursor.getString(cursor
						.getColumnIndex("ideacontent")));
				ideaData.setDate(cursor.getString(cursor.getColumnIndex("date")));
				list.add(ideaData);
			} while (cursor.moveToNext());
		}
		return list;
	}
}

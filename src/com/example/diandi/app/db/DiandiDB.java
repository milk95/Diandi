package com.example.diandi.app.db;

import java.util.ArrayList;
import java.util.List;

import com.example.diandi.app.model.AccountInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DiandiDB {

	/*
	 * ���ݿ���
	 */
	public static final String DB_NAME="diandi";
	
	/*
	 * ���ݿ�汾
	 */
	public static final int VERSION=1;
	
	private static DiandiDB diandiDB;
	private SQLiteDatabase db;
	
	/*
	 * ���췽��˽�л�
	 */
	private  DiandiDB(Context context){
		DiandiOpenHelper dbHelper=new DiandiOpenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
	}
	/*
	 * ��ȡDiandiDB��ʵ��
	 */
	public synchronized static DiandiDB getInstance(Context context){
		
		if(diandiDB==null){
			diandiDB=new DiandiDB(context);
		}
		return diandiDB;
	}
	
	/*
	 * ��AccountInfo�浽���ݿ���
	 */
	public void saveAccountInfo(AccountInfo accountInfo){
		if(accountInfo!=null) {
			ContentValues values=new ContentValues();
			values.put("address", accountInfo.getAddress());
			values.put("password", accountInfo.getPassword());
			values.put("username", accountInfo.getName());
			db.insert("AccountInfo", null, values);
		}
	}
	/*
	 * �����ݿ��ȡ�����˻�����Ϣ
	 */
	public List<AccountInfo> loadAccountInfos() {
		List<AccountInfo>  list=new ArrayList<AccountInfo>();
		Cursor cursor=db.query("AccountInfo",null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				AccountInfo accountInfo=new AccountInfo();
				accountInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
				accountInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
				accountInfo.setPassword(cursor.getString(cursor.getColumnIndex("password")));
				accountInfo.setName(cursor.getString(cursor.getColumnIndex("username")));
				list.add(accountInfo);
			}while(cursor.moveToNext());
		}
		
		return list;
	}
}


















 

package com.example.diandi.app.activity;

import android.app.Application;

/*
 * ȫ�ֱ�������ȡ�˿̵�¼�˻�
 */
public class MyApplication extends Application{
	
	private String address;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		setAddress(null);
	}
	
	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address=address;
	}
	
	
}

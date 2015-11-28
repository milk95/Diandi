package com.example.diandi.app.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.example.diandi.R;
import com.example.diandi.app.content.EditSay;
import com.example.diandi.app.content.ShowSayItem;
import com.example.diandi.app.db.DiandiDB;
import com.example.diandi.app.model.SayData;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import android.widget.ImageButton;

import android.widget.ListView;

import android.widget.SimpleAdapter;

public class SayContent extends Activity implements OnClickListener,
		OnItemClickListener {

	private static final int REQUEST_CODE = 1;

	/*
	 * 系统日期
	 */
	private SimpleDateFormat sdf;
	/*
	 * 更新listView 的string
	 */
	private String sentence;
	/*
	 * 登录的账号
	 */
	private MyApplication app;

	/*
	 * 适配器
	 */
	private SimpleAdapter adapter;

	/*
	 * 显示记录的listView
	 */
	private ListView sayContentLv;

	/*
	 * lisiView的数据源itemList
	 */
	private ArrayList<HashMap<String, Object>> itemList; // ListView的数据源，这里是一个HashMap的列表
	/*
	 * 添加记录按钮
	 */
	private ImageButton addSayBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.say_content_layout);

		/*
		 * 获取系统日期
		 */
		sdf = new SimpleDateFormat("yyyy.M.d",
				Locale.CHINA);
		/*
		 * 获取登陆的账号
		 */
		app = (MyApplication) getApplication();
		/*
		 * 初始化listVIew
		 */
		sayContentLv = (ListView) findViewById(R.id.say_content_lv);
		/*
		 * 初始化数据源的list
		 */
		itemList = new ArrayList<HashMap<String, Object>>();

		/*
		 * 从数据库读取登录账号已存储的记录条
		 */
		List<SayData> dataList = new ArrayList<SayData>();
		dataList = DiandiDB.getInstance(this).loadSayDatas();
		if (dataList.size() > 0) {
			for (SayData sayData : dataList) {
				if (sayData.getNowAddress().equals(app.getAddress())) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("text", sayData.getSaySentence());
					map.put("time", sayData.getSayEditTime());
					itemList.add(0,map);
				}
			}
		}
		
		

		/*
		 * 初始化适配器
		 */
		adapter = new SimpleAdapter(this, itemList, R.layout.say_item_layout,
				new String[] { "text","time" }, new int[] {
						R.id.say_item_content_tv,R.id.say_item_time_tv});
		/*
		 * 绑定适配器
		 */
		sayContentLv.setAdapter(adapter);

		/*
		 * 初始化、监听添加记录按钮
		 */
		addSayBt = (ImageButton) findViewById(R.id.add_say_button);
		addSayBt.setOnClickListener(this);

		/*
		 * 监听listView每一项
		 */
		sayContentLv.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_say_button:
			Intent intent = new Intent(this, EditSay.class);
			startActivityForResult(intent, REQUEST_CODE);
		default:
			break;
		}
	}

	/*
	 * 接受添加listView新的item返回的结果
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE && resultCode == 2) {
			sentence = data.getStringExtra("sentence");
			addItem();
		}
	}

	/*
	 * 在listView第一个位置添加一条记录
	 */
	private void addItem() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("text", sentence);
		map.put("time", sdf.format(new Date()));
		itemList.add(0,map);
		adapter.notifyDataSetChanged();
	}

	/*private void deleteItem() {
		int size = itemList.size();
		if (size > 0) {
			itemList.remove(itemList.size() - 1);
			adapter.notifyDataSetChanged();
		}
	}*/

	/*
	 * listView的item的点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		LinearLayout layout=(LinearLayout) sayContentLv.getChildAt(arg2);
		TextView sentenceTv=(TextView) layout.findViewById(R.id.say_item_content_tv);
		TextView timeTv=(TextView) layout.findViewById(R.id.say_item_time_tv);
		Intent showIntent = new Intent();
		showIntent.putExtra("sentence", sentenceTv.getText().toString());
		showIntent.putExtra("time", timeTv.getText().toString());
		showIntent.setClass(SayContent.this, ShowSayItem.class);
		startActivity(showIntent);
	}

}

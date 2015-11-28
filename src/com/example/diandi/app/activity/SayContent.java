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
	 * ϵͳ����
	 */
	private SimpleDateFormat sdf;
	/*
	 * ����listView ��string
	 */
	private String sentence;
	/*
	 * ��¼���˺�
	 */
	private MyApplication app;

	/*
	 * ������
	 */
	private SimpleAdapter adapter;

	/*
	 * ��ʾ��¼��listView
	 */
	private ListView sayContentLv;

	/*
	 * lisiView������ԴitemList
	 */
	private ArrayList<HashMap<String, Object>> itemList; // ListView������Դ��������һ��HashMap���б�
	/*
	 * ��Ӽ�¼��ť
	 */
	private ImageButton addSayBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.say_content_layout);

		/*
		 * ��ȡϵͳ����
		 */
		sdf = new SimpleDateFormat("yyyy.M.d",
				Locale.CHINA);
		/*
		 * ��ȡ��½���˺�
		 */
		app = (MyApplication) getApplication();
		/*
		 * ��ʼ��listVIew
		 */
		sayContentLv = (ListView) findViewById(R.id.say_content_lv);
		/*
		 * ��ʼ������Դ��list
		 */
		itemList = new ArrayList<HashMap<String, Object>>();

		/*
		 * �����ݿ��ȡ��¼�˺��Ѵ洢�ļ�¼��
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
		 * ��ʼ��������
		 */
		adapter = new SimpleAdapter(this, itemList, R.layout.say_item_layout,
				new String[] { "text","time" }, new int[] {
						R.id.say_item_content_tv,R.id.say_item_time_tv});
		/*
		 * ��������
		 */
		sayContentLv.setAdapter(adapter);

		/*
		 * ��ʼ����������Ӽ�¼��ť
		 */
		addSayBt = (ImageButton) findViewById(R.id.add_say_button);
		addSayBt.setOnClickListener(this);

		/*
		 * ����listViewÿһ��
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
	 * �������listView�µ�item���صĽ��
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
	 * ��listView��һ��λ�����һ����¼
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
	 * listView��item�ĵ���¼�
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

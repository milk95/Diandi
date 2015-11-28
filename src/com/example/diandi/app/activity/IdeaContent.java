package com.example.diandi.app.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.diandi.R;
import com.example.diandi.app.content.EditIdea;
import com.example.diandi.app.content.ReEditIdea;
import com.example.diandi.app.db.DiandiDB;
import com.example.diandi.app.model.IdeaData;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class IdeaContent extends Activity implements OnClickListener,OnItemClickListener {

	private static final int REQUEST_CODE_ADD = 1;
	
	private static final int REQUEST_CODE_EDIT = 0;

	private ImageButton addIdeaBt;

	private MyApplication app;

	private SimpleAdapter adapter;

	private List<HashMap<String, Object>> itemList;

	private ListView ideaContenLv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.idea_content_layout);

		app = (MyApplication) getApplication();

		itemList = new ArrayList<HashMap<String, Object>>();
		ideaContenLv = (ListView) findViewById(R.id.idea_content_lv);
		addIdeaBt = (ImageButton) findViewById(R.id.idea_add_imgbt);

		List<IdeaData> list = new ArrayList<IdeaData>();
		list = DiandiDB.getInstance(this).loadIdeaDatas();
		if (list.size() > 0) {
			for (IdeaData ideaData : list) {
				if (ideaData.getNowAddress().equals(app.getAddress())) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("title", ideaData.getIdeaTitle());
					map.put("date", ideaData.getDate());
					itemList.add(0, map);
				}
			}
		}

		adapter = new SimpleAdapter(this, itemList, R.layout.idea_item_layout,
				new String[] { "title", "date" }, new int[] {
						R.id.idea_item_title_tv, R.id.idea_item_date_tv });

		ideaContenLv.setAdapter(adapter);

		addIdeaBt.setOnClickListener(this);
		ideaContenLv.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.idea_add_imgbt:
			Intent intent = new Intent(this, EditIdea.class);
			startActivityForResult(intent, REQUEST_CODE_ADD);
		}
	}

	public void addItem(String title, String date) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("date", date);
		itemList.add(0, map);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_ADD && resultCode == 2) {
			String title = data.getStringExtra("ideatitle");
			String date = data.getStringExtra("editdate");
			addItem(title, date);
		}
		
		else if(requestCode == REQUEST_CODE_EDIT && resultCode == 3){
			String title=data.getStringExtra("ideatitle");
			String date=data.getStringExtra("editdate");
			int position = 0;
			data.getIntExtra("position", position);
			LinearLayout layout=(LinearLayout) ideaContenLv.getChildAt(position);
			TextView titleTv=(TextView) layout.findViewById(R.id.idea_item_title_tv);
			TextView editDate=(TextView) layout.findViewById(R.id.idea_item_date_tv);
			titleTv.setText(title);
			editDate.setText(date);
		}
	}
	
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		LinearLayout layout=(LinearLayout) ideaContenLv.getChildAt(arg2);
		TextView titleTv=(TextView) layout.findViewById(R.id.idea_item_title_tv);
		Intent intent=new Intent(this, ReEditIdea.class);
		intent.putExtra("title", titleTv.getText().toString());
		intent.putExtra("position", arg2);
		startActivityForResult(intent, REQUEST_CODE_EDIT);
	}
}









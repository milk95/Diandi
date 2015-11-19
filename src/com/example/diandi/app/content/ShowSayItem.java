package com.example.diandi.app.content;

import com.example.diandi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class ShowSayItem extends Activity{
	
	private TextView sayItemTv;
	private TextView sayTimeTv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.say_item_show_layout);
		
		sayTimeTv=(TextView) findViewById(R.id.show_say_date_tv);
		sayItemTv=(TextView) findViewById(R.id.show_say_item_tv);
		
		Intent intent = this.getIntent();
		String sentence = intent.getStringExtra("sentence");
		String time=intent.getStringExtra("time");
		sayTimeTv.setText(time);
		sayItemTv.setText(sentence);
		
	}

}

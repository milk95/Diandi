package com.example.diandi.app.activity;

import com.example.diandi.R;
import com.example.diandi.app.content.EditSay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SayContent extends Activity implements OnClickListener {
	
	/*
	 * Ìí¼Ó¼ÇÂ¼°´Å¥
	 */
	private ImageButton addSayBt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.say_content_layout);
		
	    addSayBt= (ImageButton) findViewById(R.id.add_say_button);
	    addSayBt.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.add_say_button:
			Intent intent=new Intent(SayContent.this,EditSay.class);
		    startActivity(intent);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode == 0) { 
           
        }
	}

}

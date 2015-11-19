package com.example.diandi.app.activity;
import com.example.diandi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class LandscapeContent extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.landscape_content_layout);
	}

}

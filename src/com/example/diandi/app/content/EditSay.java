package com.example.diandi.app.content;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.diandi.R;
import com.example.diandi.app.activity.MyApplication;
import com.example.diandi.app.db.DiandiDB;
import com.example.diandi.app.model.SayData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditSay extends Activity implements OnClickListener {

	/*
	 * 登录的账号
	 */
	private MyApplication app;
	/*
	 * 系统日期
	 */
	SimpleDateFormat sdf;
	
	
	private Button sayCancleBt;
	private Button saySaveBt;
	private EditText sayContentEt;
	private TextView sayEditTimeTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_say_layout);

		/*
		 * 初始化
		 */
		app = (MyApplication) getApplication();
		sayEditTimeTv=(TextView) findViewById(R.id.say_edit_time_tv);
		sayContentEt = (EditText) findViewById(R.id.say_content_edit);
		sayCancleBt = (Button) findViewById(R.id.say_cancle_button);
		saySaveBt = (Button) findViewById(R.id.say_save_button);
		/*
		 * 获取系统时间
		 */
		sdf = new SimpleDateFormat("yyyy.M.d",
				Locale.CHINA);
		/*
		 * textView设置系统时间
		 */
		sayEditTimeTv.setText(sdf.format(new Date()));
		/*
		 * 将光标移到最后一个字符后面
		 */
		sayContentEt.setSelection(sayContentEt.getText().length());

		/*
		 * 监听按钮
		 */
		sayCancleBt.setOnClickListener(this);
		saySaveBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.say_cancle_button:
			querySave();
			break;

		case R.id.say_save_button:
			saveSayContent();
			break;
		default:
			break;
		}
	}

	private void saveSayContent() {
		SayData sayData = new SayData();
		sayData.setSaySentence(sayContentEt.getText().toString());
		sayData.setSayEditTime(sdf.format(new Date()));
		sayData.setNowAddress(app.getAddress());
		
	    DiandiDB.getInstance(EditSay.this).saveSayData(sayData);
	    
		Intent intent = new Intent();
		intent.putExtra("sentence", sayContentEt.getText().toString());
		setResult(2, intent);

		finish();
	}

	private void querySave() {

		new AlertDialog.Builder(EditSay.this)
				.setTitle("系统提示")
				// 设置对话框标题

				.setMessage("是否已保存内容")
				// 设置显示的内容

				.setPositiveButton("确定", new DialogInterface.OnClickListener() {// 添加确定按钮

							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 确定按钮的响应事件

								// TODO Auto-generated method stub

								finish();

							}

						})
				.setNegativeButton("返回", new DialogInterface.OnClickListener() {// 添加返回按钮

							@Override
							public void onClick(DialogInterface dialog,
									int which) {// 响应事件

								// TODO Auto-generated method stub

							}

						}).show();// 在按键响应事件中显示此对话框
	}

}

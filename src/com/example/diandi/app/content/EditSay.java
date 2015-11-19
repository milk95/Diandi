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
	 * ��¼���˺�
	 */
	private MyApplication app;
	/*
	 * ϵͳ����
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
		 * ��ʼ��
		 */
		app = (MyApplication) getApplication();
		sayEditTimeTv=(TextView) findViewById(R.id.say_edit_time_tv);
		sayContentEt = (EditText) findViewById(R.id.say_content_edit);
		sayCancleBt = (Button) findViewById(R.id.say_cancle_button);
		saySaveBt = (Button) findViewById(R.id.say_save_button);
		/*
		 * ��ȡϵͳʱ��
		 */
		sdf = new SimpleDateFormat("yyyy.M.d",
				Locale.CHINA);
		/*
		 * textView����ϵͳʱ��
		 */
		sayEditTimeTv.setText(sdf.format(new Date()));
		/*
		 * ������Ƶ����һ���ַ�����
		 */
		sayContentEt.setSelection(sayContentEt.getText().length());

		/*
		 * ������ť
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
				.setTitle("ϵͳ��ʾ")
				// ���öԻ������

				.setMessage("�Ƿ��ѱ�������")
				// ������ʾ������

				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {// ���ȷ����ť

							@Override
							public void onClick(DialogInterface dialog,
									int which) {// ȷ����ť����Ӧ�¼�

								// TODO Auto-generated method stub

								finish();

							}

						})
				.setNegativeButton("����", new DialogInterface.OnClickListener() {// ��ӷ��ذ�ť

							@Override
							public void onClick(DialogInterface dialog,
									int which) {// ��Ӧ�¼�

								// TODO Auto-generated method stub

							}

						}).show();// �ڰ�����Ӧ�¼�����ʾ�˶Ի���
	}

}

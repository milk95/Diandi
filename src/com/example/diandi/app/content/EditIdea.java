package com.example.diandi.app.content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.example.diandi.R;
import com.example.diandi.app.activity.MyApplication;
import com.example.diandi.app.db.DiandiDB;
import com.example.diandi.app.model.IdeaData;

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
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class EditIdea extends Activity implements OnClickListener{

	private EditText ideaTitleEt;
	
	private EditText ideaContentEt;
	
	private Button  ideaCancleBt;
	
	private Button ideaSaveBt;
	
	private SimpleDateFormat sdf;
	
	private MyApplication app;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_idea_layout);
		
		sdf = new SimpleDateFormat("yyyy.M.d",
				Locale.CHINA);
		app=(MyApplication) getApplication();
		
		
		ideaTitleEt=(EditText) findViewById(R.id.idea_title_et);
		ideaContentEt=(EditText) findViewById(R.id.idea_content_et);
		
		
		ideaCancleBt=(Button) findViewById(R.id.idea_cancle_button);
		ideaSaveBt=(Button) findViewById(R.id.idea_save_button);
		
		ideaCancleBt.setOnClickListener(this);
		ideaSaveBt.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.idea_save_button:
				
			saveIdea();
			break;
			
		case R.id.idea_cancle_button:
			querySaved();
			break;
		default:
			break;
		}
	}


	private void querySaved() {
		new AlertDialog.Builder(EditIdea.this)
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

	private void saveIdea() {
		String ideaTitle=ideaTitleEt.getText().toString();
		String ideaContent=ideaContentEt.getText().toString();
		IdeaData ideaData=new IdeaData();
		ideaData.setDate(sdf.format(new Date()));
		ideaData.setNowAddress(app.getAddress());
		ideaData.setIdeaTitle(ideaTitle);
		ideaData.setIdeaContent(ideaContent);
		DiandiDB.getInstance(this).saveIdeaData(ideaData);
		
		Intent intent = new Intent();
		intent.putExtra("ideatitle", ideaTitleEt.getText().toString());
		intent.putExtra("editdate", sdf.format(new Date()));
		setResult(2, intent);

		finish();
	}
	
}









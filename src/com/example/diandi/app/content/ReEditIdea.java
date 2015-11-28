package com.example.diandi.app.content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReEditIdea extends Activity implements OnClickListener {

	private EditText ideaTitleEt;

	private EditText ideaContentEt;

	private Button ideaCancleBt;

	private Button ideaSaveBt;

	private SimpleDateFormat sdf;

	private MyApplication app;

	private String title;
	
	private int position;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_idea_layout);

		sdf = new SimpleDateFormat("yyyy.M.d", Locale.CHINA);
		app = (MyApplication) getApplication();

		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		intent.getIntExtra("position",position);

		ideaTitleEt = (EditText) findViewById(R.id.idea_title_et);
		ideaContentEt = (EditText) findViewById(R.id.idea_content_et);
		ideaContentEt.setSelection(ideaContentEt.getText().length());
		
		ideaCancleBt = (Button) findViewById(R.id.idea_cancle_button);
		ideaSaveBt = (Button) findViewById(R.id.idea_save_button);
		
		setRecord();
		
		ideaCancleBt.setOnClickListener(this);
		ideaSaveBt.setOnClickListener(this);
	}

	private void setRecord() {
		List<IdeaData> list = new ArrayList<IdeaData>();
		list = DiandiDB.getInstance(this).loadIdeaDatas();
		if (list.size() > 0) {
			for (IdeaData ideaData : list) {
				if (ideaData.getIdeaTitle().equals(title)) {
					Toast.makeText(this,ideaData.getIdeaContent() , Toast.LENGTH_SHORT).show();
					ideaTitleEt.setText(ideaData.getIdeaTitle());
					ideaContentEt.setText(ideaData.getIdeaContent());
					break;
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.idea_save_button:

			editIdea();
			break;

		case R.id.idea_cancle_button:
			querySaved();
			break;
		default:
			break;
		}
	}

	private void editIdea() {
		String ideaTitle = ideaTitleEt.getText().toString();
		String ideaContent = ideaContentEt.getText().toString();
		IdeaData ideaData = new IdeaData();
		ideaData.setDate(sdf.format(new Date()));
		ideaData.setIdeaTitle(ideaTitle);
		ideaData.setIdeaContent(ideaContent);
		DiandiDB.getInstance(this).updateIdeaData(ideaData, ideaTitle);

		Intent intent = new Intent();
		intent.putExtra("ideatitle", ideaTitleEt.getText().toString());
		intent.putExtra("editdate", sdf.format(new Date()));
		intent.putExtra("position", position);
		setResult(3, intent);

		finish();
	}

	private void querySaved() {
		new AlertDialog.Builder(ReEditIdea.this)
				.setTitle("ϵͳ��ʾ")
				// ���öԻ������

				.setMessage("�Ƿ��ѱ������")
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

package com.example.diandi.app.layout;

import com.example.diandi.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginDialog extends Dialog implements
		android.view.View.OnClickListener {

	/*
	 * ��¼�˺�����
	 */
	private EditText loginAddressEt;
	private EditText loginPasswordEt;
	/*
	 * dialog�����¼��ť
	 */
	private Button loginBt;

	public LoginDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_layout);

		/*
		 * ����LoginDialog������
		 */
		setCancelable(true);
		setTitle("��¼");
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		params.width = 800;
		params.height = 600;
		this.getWindow().setAttributes(params);

		/*
		 * ��ʼ�����ؼ�
		 */
		loginAddressEt = (EditText) findViewById(R.id.login_mailaddress_edit);
		loginPasswordEt = (EditText) findViewById(R.id.login_password_edit);
		loginBt = (Button) findViewById(R.id.login_button);
		
		/*
		 * ����dialog��¼��ť
		 */
		loginBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.login_button) {
			/*
			 * ����õ����ݴ���
			 */
			Intent loginIntent = new Intent();
			loginIntent.setAction("LOGIN");
			loginIntent.putExtra("login_address", loginAddressEt.getText()
					.toString());
			loginIntent.putExtra("login_password", loginPasswordEt.getText()
					.toString());
			LoginDialog.this.getContext().sendBroadcast(loginIntent);
			dismiss();
		}
	}

}

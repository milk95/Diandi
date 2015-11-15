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
	 * 登录账号输入
	 */
	private EditText loginAddressEt;
	private EditText loginPasswordEt;
	/*
	 * dialog界面登录按钮
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
		 * 设置LoginDialog的属性
		 */
		setCancelable(true);
		setTitle("登录");
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		params.width = 800;
		params.height = 600;
		this.getWindow().setAttributes(params);

		/*
		 * 初始化各控件
		 */
		loginAddressEt = (EditText) findViewById(R.id.login_mailaddress_edit);
		loginPasswordEt = (EditText) findViewById(R.id.login_password_edit);
		loginBt = (Button) findViewById(R.id.login_button);
		
		/*
		 * 监听dialog登录按钮
		 */
		loginBt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.login_button) {
			/*
			 * 将获得的数据传回
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

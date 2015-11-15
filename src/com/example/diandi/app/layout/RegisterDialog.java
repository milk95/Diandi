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



public class RegisterDialog extends Dialog implements android.view.View.OnClickListener{

	/*
	 * dialogע�ᰴť
	 */
	private Button registerBt;
	
	/*
	 * ע����Ϣ����
	 */
	private EditText reAddressEt;
	private EditText rePasswordEt;
	private EditText reNameEt;
	
	public RegisterDialog(Context context) {
		super(context);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_layout);

		/*
		 * ����RegisterDialog������
		 */
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		params.width = 800;
		params.height = 800;
		this.getWindow().setAttributes(params);
		
		/*
		 * ��ʼ�����ؼ�
		 */
		reAddressEt=(EditText) findViewById(R.id.register_mailaddress_edit);
		rePasswordEt=(EditText) findViewById(R.id.register_password_edit);
		reNameEt=(EditText) findViewById(R.id.regester_name_edit);
		registerBt=(Button) findViewById(R.id.regester_button);
		
		/*
		 * ����dialogע�ᰴť
		 */
		registerBt.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.regester_button){
			/*
			 * ����õ������Թ㲥��ʽ����
			 */
			Intent registerIntent = new Intent();
			registerIntent.setAction("REGISTER");
			registerIntent.putExtra("register_address", reAddressEt.getText()
					.toString());
			registerIntent.putExtra("register_password", rePasswordEt.getText()
					.toString());
			registerIntent.putExtra("register_name", reNameEt.getText()
					.toString());
			RegisterDialog.this.getContext().sendBroadcast(registerIntent);
			dismiss();
		}
	}

}
















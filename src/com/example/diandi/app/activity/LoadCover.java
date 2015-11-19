package com.example.diandi.app.activity;

import java.util.List;

import com.example.diandi.app.db.DiandiDB;
import com.example.diandi.app.layout.LoginDialog;
import com.example.diandi.app.layout.RegisterDialog;
import com.example.diandi.app.model.AccountInfo;
import com.example.diandi.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class LoadCover extends Activity implements OnClickListener{

	private MyApplication app;
    /*
     * ���� ��¼��ע��İ�ť
     */
	private Button coverLoginBt;
	private Button coverRegisterBt;

	/*
	 * ���ݿ�
	 */
	public DiandiDB diandiDB;
	
	/*
	 * ����ע��͵�¼dialog���صĹ㲥
	 */
	private BroadcastReceiver loginAndRegisterReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			
			if (intent.getAction().equals("LOGIN")) {
				boolean loginState=false;
				String address = intent.getStringExtra("login_address");
				String password = intent.getStringExtra("login_password");
				
			
				/*
				 * ��֤�˺������׼ȷ��
				 */
				List<AccountInfo> accountList=diandiDB.loadAccountInfos();
				if(accountList.size()>0){
					for(AccountInfo account:accountList){
						if(account.getAddress().equals(address)&&account.getPassword().equals(password)){
							loginState=true;
							app.setAddress(account.getAddress());
						}
					}
				}
				if(loginState){
					Intent primaryIntent=new Intent(LoadCover.this, Primary.class);
					startActivity(primaryIntent);
				}else{
					Toast.makeText(LoadCover.this, "��¼ʧ��", Toast.LENGTH_SHORT).show();
				}
				
			}else if(intent.getAction().equals("REGISTER")){
				boolean registerState=true;
				AccountInfo accountInfo=new AccountInfo();
				accountInfo.setAddress(intent.getStringExtra("register_address"));
				accountInfo.setPassword(intent.getStringExtra("register_password"));
				accountInfo.setName(intent.getStringExtra("register_name"));
				/*
				 * ��֤ע�������Ƿ��ѱ�ע��
				 */
				List<AccountInfo> accountList=diandiDB.loadAccountInfos();
				if(accountList.size()>0){
					for(AccountInfo account:accountList){
						if(account.getAddress().equals(accountInfo.getAddress())){
							Toast.makeText(LoadCover.this, "ע��ʧ��", Toast.LENGTH_SHORT).show();
							registerState=false;
						}
					}
				}
				if(registerState){
					diandiDB.saveAccountInfo(accountInfo);
					Toast.makeText(LoadCover.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
				}
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cover_layout);

		app=(MyApplication) getApplication();
		/*
		 * ���diandiDB��ʵ��
		 */
		diandiDB = DiandiDB.getInstance(this);
		
		/*
		 * ��ʼ������ĵ�¼��ע�ᰴť
		 */
		coverLoginBt = (Button) findViewById(R.id.cover_login_button);
		coverRegisterBt=(Button) findViewById(R.id.cover_register_button);

		/*
		 * ��������ĵ�¼��ע�ᰴť
		 */
		coverLoginBt.setOnClickListener(this);
		coverRegisterBt.setOnClickListener(this);
		
	}

			@Override
			public void onClick(View v) {
				
				switch (v.getId()) {

				case R.id.cover_login_button:
					IntentFilter loginFilter = new IntentFilter();
					loginFilter.addAction("LOGIN");
					v.getContext()
							.registerReceiver(loginAndRegisterReceiver, loginFilter);
					LoginDialog loginDialog = new LoginDialog(v.getContext());
					loginDialog.show();
					break;
				case R.id.cover_register_button:
					IntentFilter registerFilter = new IntentFilter();
					registerFilter.addAction("REGISTER");
					v.getContext()
							.registerReceiver(loginAndRegisterReceiver, registerFilter);
					RegisterDialog registerDialog = new RegisterDialog(v.getContext());
					registerDialog.show();
					break;
				default:
					break;
				}
			}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

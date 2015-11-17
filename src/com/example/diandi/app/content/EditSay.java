package com.example.diandi.app.content;

import com.example.diandi.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class EditSay extends Activity implements OnClickListener{
	
	private Button sayCancleBt;
	private Button saySaveBt;
	private EditText sayContentEt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_say_layout);
		
		sayContentEt=(EditText) findViewById(R.id.say_content_edit);
	    sayContentEt.setSelection(sayContentEt.getText().length()); 
		
	    sayCancleBt=(Button) findViewById(R.id.say_cancle_button);
	    saySaveBt=(Button) findViewById(R.id.say_save_button);
	    
	    sayCancleBt.setOnClickListener(this);
	    saySaveBt.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
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
		// TODO Auto-generated method stub
		
	}
	private void querySave() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub  
		  
	     new AlertDialog.Builder(EditSay.this).setTitle("ϵͳ��ʾ")//���öԻ������  
	  
	     .setMessage("�Ƿ��ѱ�������")//������ʾ������  
	  
	     .setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {//���ȷ����ť  
	  
	          
	  
	         @Override  
	  
	         public void onClick(DialogInterface dialog, int which) {//ȷ����ť����Ӧ�¼�  
	  
	             // TODO Auto-generated method stub  
	  
	             finish();  
	  
	         }  
	  
	     }).setNegativeButton("����",new DialogInterface.OnClickListener() {//��ӷ��ذ�ť  
	  
	          
	  
	         @Override  
	         public void onClick(DialogInterface dialog, int which) {//��Ӧ�¼�  
	  
	             // TODO Auto-generated method stub 
	           
	         }  
	  
	     }).show();//�ڰ�����Ӧ�¼�����ʾ�˶Ի��� 
	}

}















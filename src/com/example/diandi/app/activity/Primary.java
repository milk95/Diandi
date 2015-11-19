package com.example.diandi.app.activity;

import com.example.diandi.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class Primary extends Activity {

	/*
	 * ���������ĵ�context
	 */
	private Context mContext = null;

	private PopupWindow popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.primarypage_layout);

		/*
		 * ��ʼ��
		 */
		mContext = this;

		/*
		 * ��ʼ��menuBt,���˵���ť
		 */
		ImageButton menuBt = (ImageButton) findViewById(R.id.left_menu_button);

		/*
		 * ����menuBt,
		 */
		menuBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				showPopupWindow(view);
			}
		});
	}

	/*
	 * ���˵������ķ���
	 */
	private void showPopupWindow(View view) {
		// һ���Զ���Ĳ��֣���Ϊ��ʾ������
		View contentView = LayoutInflater.from(mContext).inflate(
				R.layout.left_menu_layout, null, false);
		/*
		 * ����popupWindow���֣��������䰵
		 */
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.7f;
		getWindow().setAttributes(lp);

		

		/*
		 * ���÷羰��ť�ļ����¼�
		 */
		Button landscapeItemBt = (Button) contentView
				.findViewById(R.id.landscape_item_button);
		landscapeItemBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, LandscapeContent.class);
				startActivity(intent);
			}
		});

		/*
		 * ����Ƭ�ﰴť�ļ����¼�
		 */
		Button sayItemBt = (Button) contentView
				.findViewById(R.id.say_item_button);
		sayItemBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext, SayContent.class);
				startActivity(intent);
			}
		});

		// ���ÿ���
		popupWindow = new PopupWindow(contentView, 900,
				LayoutParams.MATCH_PARENT, true);

		// ���ö���Ч��
		popupWindow.setAnimationStyle(R.style.AnimationFade);

		// ��ʾ����Ļ�����
		popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);

		// ��������ط���ʧ
		contentView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (popupWindow != null && popupWindow.isShowing()) {
					popupWindow.dismiss();
					popupWindow = null;
				}
				return false;
			}
		});

		/*
		 * ����popupWindow��ʧ��������ɫ�ָ�
		 */
		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1f;
				getWindow().setAttributes(lp);
			}
		});
	}
}

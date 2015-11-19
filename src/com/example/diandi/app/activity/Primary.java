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
	 * 传递上下文的context
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
		 * 初始化
		 */
		mContext = this;

		/*
		 * 初始化menuBt,左侧菜单按钮
		 */
		ImageButton menuBt = (ImageButton) findViewById(R.id.left_menu_button);

		/*
		 * 监听menuBt,
		 */
		menuBt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				showPopupWindow(view);
			}
		});
	}

	/*
	 * 左侧菜单动作的方法
	 */
	private void showPopupWindow(View view) {
		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(mContext).inflate(
				R.layout.left_menu_layout, null, false);
		/*
		 * 设置popupWindow出现，背景即变暗
		 */
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.7f;
		getWindow().setAttributes(lp);

		

		/*
		 * 设置风景按钮的监听事件
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
		 * 设置片语按钮的监听事件
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

		// 设置宽、高
		popupWindow = new PopupWindow(contentView, 900,
				LayoutParams.MATCH_PARENT, true);

		// 设置动画效果
		popupWindow.setAnimationStyle(R.style.AnimationFade);

		// 显示在屏幕的左侧
		popupWindow.showAtLocation(view, Gravity.LEFT, 0, 0);

		// 点击其他地方消失
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
		 * 监听popupWindow消失，背景颜色恢复
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

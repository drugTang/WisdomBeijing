package com.lex.zhbj.pages.contentpages;

import com.lex.zhbj.R;
import com.lex.zhbj.pages.BasePage;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ServiceContentPage extends BasePage {

	public ServiceContentPage(Activity activity) {
		super(activity);
	}
	
	/**
	 * 初始化视图，只加载一次的数据可以放到里面
	 */
	@Override
	public void initView() {
		//为菜单按钮添加点击事件
		menuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.showMenu();
			}
		});
	}

	@Override
	public void initData() {
		System.out.println("初始化服务数据...");
		titleText.setText(R.string.title_smart);
		//设置SlidingMenu可用
		setSlidingMenuEnabled(true);
		
		TextView context = new TextView(mActivity);
		context.setText("服务");
		context.setTextColor(Color.RED);
		context.setGravity(Gravity.CENTER);
		context.setTextSize(24);
		
		flContent.addView(context);
	}

}

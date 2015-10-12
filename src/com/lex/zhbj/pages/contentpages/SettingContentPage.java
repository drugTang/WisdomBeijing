package com.lex.zhbj.pages.contentpages;

import com.lex.zhbj.R;
import com.lex.zhbj.pages.BasePage;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SettingContentPage extends BasePage {

	public SettingContentPage(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("初始化设置数据...");
		titleText.setText(R.string.title_setting);
		//设置菜单按钮不显示
		menuButton.setVisibility(View.GONE);
		//设置SlidingMenu不可用
		setSlidingMenuEnabled(false);
		
		TextView context = new TextView(mActivity);
		context.setText("设置");
		context.setTextColor(Color.RED);
		context.setGravity(Gravity.CENTER);
		context.setTextSize(24);
		
		flContent.addView(context);
	}

}

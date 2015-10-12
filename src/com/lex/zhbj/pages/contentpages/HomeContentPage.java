package com.lex.zhbj.pages.contentpages;

import com.lex.zhbj.R;
import com.lex.zhbj.pages.BasePage;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class HomeContentPage extends BasePage {

	public HomeContentPage(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("初始化首页数据...");
		titleText.setText(R.string.title_home);
		//设置菜单按钮不显示
		menuButton.setVisibility(View.GONE);
		setSlidingMenuEnabled(false);
		
		TextView context = new TextView(mActivity);
		context.setText("首页");
		context.setTextColor(Color.RED);
		context.setGravity(Gravity.CENTER);
		context.setTextSize(24);
		
		flContent.addView(context);
	}

}

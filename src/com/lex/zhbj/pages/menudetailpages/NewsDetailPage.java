package com.lex.zhbj.pages.menudetailpages;

import com.lex.zhbj.pages.BaseMenuDetailPage;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class NewsDetailPage extends BaseMenuDetailPage {

	public NewsDetailPage(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		TextView text = new TextView(mActivity);
		text.setText("菜单详情页-新闻");
		text.setTextSize(24);
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}

package com.lex.zhbj.pages;

import android.app.Activity;
import android.view.View;

public abstract class BaseMenuDetailPage {
	public Activity mActivity;
	public View mRootView;
	
	public BaseMenuDetailPage(Activity activity) {
		mActivity = activity;
		mRootView = initView();
	}

	public abstract View initView();

	public void initData() {
	}
}

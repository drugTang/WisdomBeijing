package com.lex.zhbj.pages;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lex.zhbj.R;
import com.lex.zhbj.activity.MainActivity;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePage {
	/**
	 * Activity 对象
	 */
	public Activity mActivity;

	/**
	 * 根视图
	 */
	public View mRootView;
	/**
	 * 菜单按钮
	 */
	public ImageButton menuButton;
	/**
	 * 标题文本
	 */
	public TextView titleText;
	/**
	 * 帧布局
	 */
	public FrameLayout flContent;
	
	public SlidingMenu slidingMenu;

	/**
	 * 初始化一些必要数据
	 * @param activity
	 */
	public BasePage(Activity activity) {
		this.mActivity = activity;
		MainActivity mainActivity = (MainActivity) mActivity;
		slidingMenu = mainActivity.slidingMenu;
		mRootView = View.inflate(mActivity, R.layout.base_page, null);
		menuButton = (ImageButton) mRootView.findViewById(R.id.image_menu);
		titleText = (TextView) mRootView.findViewById(R.id.tv_title);
		flContent = (FrameLayout) mRootView.findViewById(R.id.fl_context);
		initView();
	}

	/**
	 * 初始化视图
	 */
	public void initView() {
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
	}
	
	/**
	 * 
	 */

	/**
	 * 设置侧边栏是否可用
	 */
	public void setSlidingMenuEnabled(boolean enabled) {
		if (enabled) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

}

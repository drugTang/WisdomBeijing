package com.lex.zhbj.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lex.zhbj.R;
import com.lex.zhbj.fragment.impl.ContentFragment;
import com.lex.zhbj.fragment.impl.LeftMenuFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	public SlidingMenu slidingMenu;
	private FragmentManager fragmentManager;
	
	private String LEFT_MENU_FRAGMENT_TAG = "left_menu_fragment_tag";
	private String CONTENT_FRAGMENT_TAG = "content_fragment_tag";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);

		slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT);// 设置侧边栏样式
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏范围滑动出现侧边栏
		slidingMenu.setBehindOffset(400);// 设置预留宽度
		initFragment();
	}

	/**
	 * 初始化Fragment
	 */
	private void initFragment() {
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction tr = fragmentManager.beginTransaction();
		tr.replace(R.id.fl_content, new ContentFragment(), CONTENT_FRAGMENT_TAG);
		tr.replace(R.id.fl_left_menu, new LeftMenuFragment(), LEFT_MENU_FRAGMENT_TAG);
		tr.commit();
	}
	
	/**
	 * 获取侧边栏Fragment
	 */
	public LeftMenuFragment getLeftMenuFragment() {
		return (LeftMenuFragment)fragmentManager.findFragmentByTag(LEFT_MENU_FRAGMENT_TAG);
	}
	
	/**
	 * 获取内容Fragment
	 */
	public ContentFragment getContentFragment() {
		return (ContentFragment)fragmentManager.findFragmentByTag(CONTENT_FRAGMENT_TAG);
	}
}

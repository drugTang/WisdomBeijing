package com.lex.zhbj.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lex.zhbj.R;
import com.lex.zhbj.fragment.ContentFragment;
import com.lex.zhbj.fragment.LeftMenuFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SlidingFragmentActivity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);
		
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setMode(SlidingMenu.LEFT);//设置侧边栏样式
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏范围滑动出现侧边栏
		slidingMenu.setBehindOffset(200);//设置预留宽度
		initFragment();
	}
	
	private void initFragment() {
		FragmentManager manger = getSupportFragmentManager();
		FragmentTransaction tr = manger.beginTransaction();
		tr.replace(R.id.fl_content, new ContentFragment(), "content_fragment_tag");
		tr.replace(R.id.fl_left_menu, new LeftMenuFragment(),"left_menu_fragment_tag");
		tr.commit();
	}
}

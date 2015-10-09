package com.lex.zhbj.activity;

import java.util.ArrayList;
import java.util.List;

import com.lex.zhbj.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 新手引导页
 * @author Administrator
 *
 */
public class GuideActivity extends Activity{
	private int[] mImageIds = new int[] {R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	/**
	 * 新手引导页
	 */
	private ViewPager viewPager;
	/**
	 * ViewPager Adapter
	 */
	private GuideAdapter guideAdapter;
	/**
	 * 开始体验按钮
	 */
	private Button startButton;
	/**
	 * 
	 */
	private List<ImageView> mImageViewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		viewPager = (ViewPager)findViewById(R.id.vp_guide);
		startButton = (Button)findViewById(R.id.btn_start);
		initGuide();
		guideAdapter = new GuideAdapter();
		viewPager.setAdapter(guideAdapter);
	}
	
	/**
	 * 初始化新手引导页
	 */
	private void initGuide() {
		mImageViewList = new ArrayList<ImageView>();
		for(int i = 0;i < mImageIds.length;i++) {
			ImageView img = new ImageView(this);
			img.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(img);
		}
	}
	
	/**
	 * ViewPager Adapter
	 * @author Administrator
	 *
	 */
	private class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mImageIds.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}
		
	}
}

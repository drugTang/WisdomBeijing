package com.lex.zhbj.activity;

import java.util.ArrayList;
import java.util.List;

import com.lex.zhbj.R;
import com.lex.zhbj.utils.PreferencesUtil;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 新手引导页
 * 
 * @author Administrator
 *
 */
public class GuideActivity extends Activity {
	private int[] mImageIds = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
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
	 * 引导页下部对应点Layout
	 */
	private LinearLayout llPoints;
	/**
	 * 小红点
	 */
	private View pointView;
	/**
	 * 小红点需要移动的宽度
	 */
	private int mPointWidth;
	/**
	 * 
	 */
	private List<ImageView> mImageViewList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		viewPager = (ViewPager) findViewById(R.id.vp_guide);
		startButton = (Button) findViewById(R.id.btn_start);
		llPoints = (LinearLayout) findViewById(R.id.ll_points);
		pointView = (View) findViewById(R.id.view_point);
		
		initGuide();
		guideAdapter = new GuideAdapter();
		viewPager.setAdapter(guideAdapter);
		viewPager.addOnPageChangeListener(new MyPagerChangeListener());
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PreferencesUtil.setBoolean(GuideActivity.this, "is_user_guide_showed", true);
				Intent intent = new Intent(GuideActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
			
		});
	}

	/**
	 * 初始化新手引导页
	 */
	private void initGuide() {
		mImageViewList = new ArrayList<ImageView>();
		// 初始化引导页图片
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView img = new ImageView(this);
			img.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(img);
		}
		// 初始化引导页的小圆点
		for (int i = 0; i < mImageIds.length; i++) {
			View view = new ImageView(this);
			view.setBackgroundResource(R.drawable.guide_gray_shape);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			// 设置圆点之间的间距
			if (i > 0) {
				params.leftMargin = 10;
			}
			view.setLayoutParams(params);
			llPoints.addView(view);
		}
		//获取红色小圆点需要偏移的距离
		// 获取视图树, 对layout结束事件进行监听
		llPoints.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				//监听一次后便结束监听
				llPoints.getViewTreeObserver().removeGlobalOnLayoutListener(this);//移除监听
				//最新方法需要在API16以上使用 
				//llPoints.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				mPointWidth = llPoints.getChildAt(1).getLeft() - llPoints.getChildAt(0).getLeft();
			}
		});;
	}

	/**
	 * ViewPager change Listener
	 */
	private class MyPagerChangeListener implements OnPageChangeListener {

		/**
		 * Page滑动时调用
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//			System.out.println("当前页:" + position + ",位置偏移百分比：" + positionOffset + 
//					",位置偏移：" + positionOffsetPixels);
			int len = (int)((positionOffset+position) * mPointWidth);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) pointView.getLayoutParams();
			System.out.println("偏移量："+mPointWidth);
			params.leftMargin = len;
			pointView.setLayoutParams(params);
		}

		/**
		 * Page被选中时调用
		 */
		@Override
		public void onPageSelected(int position) {
			if (position == mImageIds.length - 1) {// 最后一个页面
				startButton.setVisibility(View.VISIBLE);// 显示开始体验的按钮
			} else {
				startButton.setVisibility(View.INVISIBLE);
			}
		}

		/**
		 * Page滑动状态改变时调用
		 */
		@Override
		public void onPageScrollStateChanged(int state) {

		}

	}

	/**
	 * ViewPager Adapter
	 * 
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
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}

	}
}

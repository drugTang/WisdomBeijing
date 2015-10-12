package com.lex.zhbj.fragment.impl;

import java.util.ArrayList;
import java.util.List;

import com.lex.zhbj.R;
import com.lex.zhbj.fragment.BaseFragment;
import com.lex.zhbj.pages.BasePage;
import com.lex.zhbj.pages.contentpages.GovAffairsContentPage;
import com.lex.zhbj.pages.contentpages.HomeContentPage;
import com.lex.zhbj.pages.contentpages.NewsCenterContentPage;
import com.lex.zhbj.pages.contentpages.ServiceContentPage;
import com.lex.zhbj.pages.contentpages.SettingContentPage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ContentFragment extends BaseFragment {
	/**
	 * ViewPager
	 */
	private ViewPager mViewPager;
	/**
	 * RadioGroup
	 */
	private RadioGroup mRadioGroup;
	/**
	 * 根视图
	 */
	private View mRootView;
	/**
	 * page页列表
	 */
	private List<BasePage> pageList = new ArrayList<BasePage>();;

	@Override
	public View initView() {
		mRootView = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (ViewPager) mRootView.findViewById(R.id.vp_content);
		mRadioGroup = (RadioGroup) mRootView.findViewById(R.id.rg_group);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.radio_home:
					mViewPager.setCurrentItem(0, false);
					break;
				case R.id.radio_news:
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.radio_smart:
					mViewPager.setCurrentItem(2, false);
					break;
				case R.id.radio_gov:
					mViewPager.setCurrentItem(3, false);
					break;
				case R.id.radio_setting:
					mViewPager.setCurrentItem(4, false);
					break;
				default:
					break;
				}
			}
		});

		return mRootView;
	}

	@Override
	public void initData() {
		// 设置默认选择首页
		mRadioGroup.check(R.id.radio_home);
		// 添加Pages
		pageList.add(new HomeContentPage(mActivity));
		pageList.add(new NewsCenterContentPage(mActivity));
		pageList.add(new ServiceContentPage(mActivity));
		pageList.add(new GovAffairsContentPage(mActivity));
		pageList.add(new SettingContentPage(mActivity));
		// 为默认页添加数据
		pageList.get(0).initData();
		// 添加适配器
		mViewPager.setAdapter(new ContentPagerAdapter());
		// 添加监听器
		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				pageList.get(arg0).initData();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});

	}
	
	/**
	 * 
	 *
	 */
	public NewsCenterContentPage getNewsCenterContentPage() {
		return (NewsCenterContentPage)pageList.get(1);
	}

	/**
	 * ViewPager适配器
	 * @author Administrator
	 *
	 */
	class ContentPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(pageList.get(position).mRootView);
			return pageList.get(position).mRootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

}

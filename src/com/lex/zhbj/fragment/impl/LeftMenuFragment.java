package com.lex.zhbj.fragment.impl;

import java.util.List;

import com.lex.zhbj.R;
import com.lex.zhbj.domain.NewsData;
import com.lex.zhbj.domain.NewsData.NewsMenuData;
import com.lex.zhbj.fragment.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends BaseFragment{
	private View mRootView;
	private List<NewsMenuData> mMenuList;
	private int mCurrentPosition;
	private MenuAdapter mAdapter;
	
	@ViewInject(R.id.lv_menu)
	public ListView menuListView;

	@Override
	public View initView() {
		mRootView = View.inflate(mActivity,R.layout.fragment_left_menu,null);
		ViewUtils.inject(this,mRootView);
		return mRootView;
	}
	
	@Override
	public void initData() {
		menuListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mCurrentPosition = position;
				mAdapter.notifyDataSetChanged();
				setCurrentMenuDetailPage(position);
			}
		});
	}
	
	/**
	 * 设置当前的菜单详情页
	 * @param position
	 */
	private void setCurrentMenuDetailPage(int position) {
		
		ContentFragment contentFragment = mActivity.getContentFragment();
		contentFragment.getNewsCenterContentPage().setCurrentMenuDetail(position);
		toggleSlidingMenu();
	}
	
	/**
	 * 变更SlidingMenu的状态
	 */
	private void toggleSlidingMenu() {
		mActivity.slidingMenu.toggle();
	}
	
	/**
	 * 
	 */
	public void setNewsData(NewsData data) {
		System.out.println(data.data);
		mMenuList = data.data;
		mAdapter = new MenuAdapter();
		menuListView.setAdapter(mAdapter);
	}
	
	class MenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mMenuList.size();
		}

		@Override
		public Object getItem(int position) {
			return mMenuList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if(convertView == null) {
				view = View.inflate(mActivity, R.layout.left_menu_list_item, null);
			} else {
				view = convertView;
			}
			TextView menuTitle = (TextView)view.findViewById(R.id.menu_title);
			menuTitle.setText(mMenuList.get(position).title);
			
			if(mCurrentPosition == position) {
				menuTitle.setEnabled(false);
			} else {
				menuTitle.setEnabled(true);
			}
			
			return view;
		}
		
	}
}

package com.lex.zhbj.pages.contentpages;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lex.zhbj.R;
import com.lex.zhbj.activity.MainActivity;
import com.lex.zhbj.domain.NewsData;
import com.lex.zhbj.fragment.impl.LeftMenuFragment;
import com.lex.zhbj.global.HttpUrlCollector;
import com.lex.zhbj.pages.BaseMenuDetailPage;
import com.lex.zhbj.pages.BasePage;
import com.lex.zhbj.pages.menudetailpages.InteractDetailPage;
import com.lex.zhbj.pages.menudetailpages.NewsDetailPage;
import com.lex.zhbj.pages.menudetailpages.PhotoDetailPage;
import com.lex.zhbj.pages.menudetailpages.TopicDetailPage;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NewsCenterContentPage extends BasePage {

	/**
	 * 
	 */
	private NewsData mNewsData;
	/**
	 * 菜单详情页集合
	 */
	private List<BaseMenuDetailPage> mPages;

	public NewsCenterContentPage(Activity activity) {
		super(activity);
	}
	
	/**
	 * 初始化视图，只加载一次的数据可以放到里面
	 */
	@Override
	public void initView() {
		//为菜单按钮添加点击事件
		menuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.showMenu();
			}
		});
	}

	@Override
	public void initData() {
		System.out.println("初始化新闻数据...");
		titleText.setText(R.string.title_news);
		// 设置SlidingMenu可用
		setSlidingMenuEnabled(true);

		TextView context = new TextView(mActivity);
		context.setText("新闻");
		context.setTextColor(Color.RED);
		context.setGravity(Gravity.CENTER);
		context.setTextSize(24);

		flContent.addView(context);
		getDataFromServer();
	}
	
	/**
	 * 从服务器获取数据
	 */
	private void getDataFromServer() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, HttpUrlCollector.CATEGORY_JSON_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				System.out.println("返回数据:" + result);
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, "从服务器获取数据失败", Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
	
	/**
	 * 转换Json数据
	 */
	private void parseData(String data) {
		Gson gson = new Gson();
		mNewsData = gson.fromJson(data, NewsData.class);
		System.out.println("解析结果：" + mNewsData);
		
		//将数据传送给侧边栏
		MainActivity mainActivity = (MainActivity)mActivity;
		LeftMenuFragment leftMenuFragment = mainActivity.getLeftMenuFragment();
		leftMenuFragment.setNewsData(mNewsData);
		
		//添加菜单详情页
		mPages = new ArrayList<BaseMenuDetailPage>();
		mPages.add(new NewsDetailPage(mActivity));
		mPages.add(new TopicDetailPage(mActivity));
		mPages.add(new PhotoDetailPage(mActivity));
		mPages.add(new InteractDetailPage(mActivity));
		//设置默认菜单详情页
		setCurrentMenuDetail(0);
	}
	
	/**
	 * 设置当前的菜单详情页
	 */
	public void setCurrentMenuDetail(int position) {
		BaseMenuDetailPage page = mPages.get(position);
		flContent.removeAllViews();//清除之前的页面
		flContent.addView(page.mRootView);
	}

}

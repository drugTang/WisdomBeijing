package com.lex.zhbj.activity;

import com.lex.zhbj.R;
import com.lex.zhbj.utils.PreferencesUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity{
	private RelativeLayout rl_root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		startAnim();
	}
	
	/**
	 * 设置动画
	 */
	private void startAnim() {
		rl_root = (RelativeLayout)findViewById(R.id.rl_root);
		//动画集合
		AnimationSet set = new AnimationSet(false);
		//设置中心旋转
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		rotate.setDuration(2000);//设置动画持续时间
		rotate.setFillAfter(true);//保持动画状态
		set.addAnimation(rotate);
		
		//设置中心缩放动画
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		scale.setDuration(2000);
		scale.setFillAfter(true);
		set.addAnimation(scale);
		
		//设置渐变动画
		AlphaAnimation alpha = new AlphaAnimation(0,1);
		alpha.setDuration(2000);
		alpha.setFillAfter(true);
		set.addAnimation(alpha);
		
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				jumpGuide();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
		});
		
		rl_root.startAnimation(set);
	}
	
	private void jumpGuide() {
		boolean isShowed = PreferencesUtil.getBoolean(this, "is_user_guide_showed", false);
		if(!isShowed) {
			Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(SplashActivity.this,MainActivity.class);
			startActivity(intent);
		}
		finish();
	}

}

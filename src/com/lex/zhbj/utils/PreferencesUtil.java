package com.lex.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtil {
	/**
	 * preferences文件名
	 */
	public static final String PREFS_NAME = "config";
	
	/**
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return prefs.getBoolean(key, defValue);
	}
	
	public static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.edit().putBoolean(key, value).commit();
	}
}

package com.decade.agile.kit;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @description: 输入键盘操作类
 * @author: Decade
 * @date: 2013-7-2
 * 
 */
public class DZKeyBoard {
	
	/**
	 * 隐藏输入键盘
	 * @param context
	 * @param edt 当前获得焦点的输入框
	 * @return
	 */
	public static boolean hideKeyBoard(Context context,EditText edt) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (edt != null) {
			return imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
		}
		return false;
	}
	
	/**
	 * 显示输入键盘
	 * @param context
	 */
	public static void showKeyBoard(Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}

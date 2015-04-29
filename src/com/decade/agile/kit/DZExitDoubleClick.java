package com.decade.agile.kit;

import android.app.Activity;
import android.content.Context;

/**
 * @description: 双击退出Activity的类。(From AndroidKit)
 * @author: Decade
 * @date: 2013-5-24
 * 
 */
public class DZExitDoubleClick extends DZDoubleClick {

	private static DZExitDoubleClick exit;

	private DZExitDoubleClick(Context context) {
		super(context);
	}

	/**
	 * 返回一个双击退出的实例。
	 * 
	 * @param context
	 * @return ExitDoubleClick
	 */
	public static synchronized DZExitDoubleClick getInstance(Context context) {
		if (exit == null) {
			exit = new DZExitDoubleClick(context);
		}
		return exit;
	}

	/**
	 * 双击之后退出。
	 */
	@Override
	protected void afterDoubleClick() {
		((Activity) mContext).finish();
		destroy();
	}

	/**
	 * 双击退出Activity，如果msg为null，而默认显示的提示语为"再按一次退出"。
	 */
	@Override
	public void doDoubleClick(int delayTime, String msg) {
		if (msg == null || msg.equals("")) {
			msg = "再按一次退出";
		}
		super.doDoubleClick(delayTime, msg);
	}

	private static void destroy() {
		exit = null;
	}
}

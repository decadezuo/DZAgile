package com.decade.agile.kit;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.decade.framework.DZApplication;
import com.decade.framework.kit.DZLog;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-7
 * 
 */
public class DZWorkspace {

	/**
	 * 获取当前手机屏幕工作空间大小，保存到App里。
	 * 
	 * @param context
	 */
	public static void saveWorkspaceSize(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		if (DZApplication.getApp() == null) {
			DZLog.e(DZWorkspace.class, "The application does not initialize");
			return;
		}
		DZLog.e(DZWorkspace.class, "density = " + dm.density + " densityDpi = "
				+ dm.densityDpi + " widthPixels = " + dm.widthPixels
				+ " heightPixels = " + dm.heightPixels);
		DZApplication.getApp().setDensity(dm.density);
		DZApplication.getApp().setWorkSpaceWidth(dm.widthPixels);
		DZApplication.getApp().setWorkSpaceHeight(
				getWorkspaceHight(activity, dm.heightPixels));
	}

	private static int getWorkspaceHight(Activity activity, int screenHight) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = activity.getResources().getDimensionPixelSize(x);
			return screenHight - sbar;
		} catch (Exception e1) {
			DZLog.e(DZWorkspace.class, "get status bar height fail");
			e1.printStackTrace();
		}
		return 770;
	}

	public static float convertSizeWithDensity(Context context, int sizeId) {
		float density = DZApplication.getApp().getDensity();
		return context.getResources().getDimension(sizeId) / density;
	}
}

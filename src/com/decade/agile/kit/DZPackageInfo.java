package com.decade.agile.kit;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-23
 */
public class DZPackageInfo {

	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	public static String getVerName(Context context) {
		try {
			String verName = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
			return verName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String getAppName(Context context) {
		return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
	}
}

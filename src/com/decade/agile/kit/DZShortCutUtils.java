package com.decade.agile.kit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-6-14
 * 
 */
public class DZShortCutUtils {
	/**
	 * 添加当前应用的桌面快捷方式
	 * 
	 * @param cx
	 */
	public static void addShortcut(Context cx,int iconId) {
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		Intent shortcutIntent = cx.getPackageManager()
				.getLaunchIntentForPackage(cx.getPackageName());
		shortcutIntent.addCategory("android.intent.category.LAUNCHER");
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		
		// 获取当前应用名称
		String title = null;
		try {
			final PackageManager pm = cx.getPackageManager();
			title = pm.getApplicationLabel(
					pm.getApplicationInfo(cx.getPackageName(),
							PackageManager.GET_META_DATA)).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 快捷方式名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		// 不允许重复创建（不一定有效）
		shortcut.putExtra("duplicate", false);
		// 快捷方式的图标
		Parcelable iconResource = Intent.ShortcutIconResource.fromContext(cx,
				iconId);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

		cx.sendBroadcast(shortcut);
	}

	/**
	 * 删除当前应用的桌面快捷方式
	 * 
	 * @param cx
	 */
	public static void delShortcut(Context cx) {
		Intent shortcut = new Intent(
				"com.android.launcher.action.UNINSTALL_SHORTCUT");

		// 获取当前应用名称
		String title = null;
		try {
			final PackageManager pm = cx.getPackageManager();
			title = pm.getApplicationLabel(
					pm.getApplicationInfo(cx.getPackageName(),
							PackageManager.GET_META_DATA)).toString();
		} catch (Exception e) {
		}
		// 快捷方式名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		Intent shortcutIntent = cx.getPackageManager()
				.getLaunchIntentForPackage(cx.getPackageName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		cx.sendBroadcast(shortcut);
	}

	/**
	 * 判断当前应用在桌面是否有桌面快捷方式 此函数只能判断少量机型，慎用
	 * 
	 * @param cx
	 */
	public static boolean hasShortcut(Context cx) {
		boolean result = false;
		String title = null;
		try {
			final PackageManager pm = cx.getPackageManager();
			title = pm.getApplicationLabel(
					pm.getApplicationInfo(cx.getPackageName(),
							PackageManager.GET_META_DATA)).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		final String AUTHORITY;
		if (android.os.Build.VERSION.SDK_INT < 8) {
			AUTHORITY = "com.android.launcher.settings";
		} else {
			AUTHORITY = "com.android.launcher2.settings";
		}
		final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
				+ "/favorites?notify=true");
		final Cursor c = cx.getContentResolver().query(CONTENT_URI, null,
				"title=?", new String[] { title }, null);
		if (c != null && c.moveToFirst()) {
			c.close();
			result = true;
		}
		return result;
	}
}

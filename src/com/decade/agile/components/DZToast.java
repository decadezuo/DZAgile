package com.decade.agile.components;

import android.content.Context;
import android.widget.Toast;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-6
 * 
 */
public class DZToast {
	private static Toast _toast;

	public static void showToastLong(Context context, String str) {
		if (_toast != null) {
			_toast.setText(str);
		} else {
			_toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
		}
		_toast.show();
	}

	public static void showToastShort(Context context, String str) {
		if (_toast != null) {
			_toast.setText(str);
		} else {
			_toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
		}
		_toast.show();
	}

	public static void showToastLong(Context context, int resId) {
		if (_toast != null) {
			_toast.setText(resId);
		} else {
			_toast = Toast.makeText(context, context.getString(resId),
					Toast.LENGTH_LONG);
		}
		_toast.show();
	}

	public static void showToastShort(Context context, int resId) {
		if (_toast != null) {
			_toast.setText(resId);
		} else {
			_toast = Toast.makeText(context, context.getString(resId),
					Toast.LENGTH_SHORT);
		}
		_toast.show();
	}

}

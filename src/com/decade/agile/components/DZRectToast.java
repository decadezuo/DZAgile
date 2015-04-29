package com.decade.agile.components;

import android.content.Context;
import android.widget.Toast;

import com.decade.agile.R;

/**
 * @description:
 * @author: Decade
 * @date: 2013-5-6
 * 
 */
public class DZRectToast {
	private static Toast _toast;

	public static void showToastLong(Context context, String str,
			ToastTheme theme) {
		show(context, str, theme, Toast.LENGTH_LONG);
	}

	public static void showToastShort(Context context, String str,
			ToastTheme theme) {
		show(context, str, theme, Toast.LENGTH_SHORT);
	}

	public static void showToastLong(Context context, int resId,
			ToastTheme theme) {
		show(context, context.getString(resId), theme, Toast.LENGTH_LONG);
	}

	public static void showToastShort(Context context, int resId,
			ToastTheme theme) {
		show(context, context.getString(resId), theme, Toast.LENGTH_SHORT);
	}

	private static void show(Context context, String str, ToastTheme theme,
			int duration) {
		if (_toast != null) {
			_toast.setText(str);
			if (theme == ToastTheme.SUCCESS) {
				((DZTipsToast) _toast)
						.setIcon(R.drawable.agile_toast_success_icon);
			} else if (theme == ToastTheme.ERROR) {
				((DZTipsToast) _toast)
						.setIcon(R.drawable.agile_toast_error_icon);
			} else if (theme == ToastTheme.SMILE) {
				((DZTipsToast) _toast)
						.setIcon(R.drawable.agile_toast_smile_icon);
			} else if (theme == ToastTheme.WARNING) {
				((DZTipsToast) _toast)
						.setIcon(R.drawable.agile_toast_warning_icon);
			}
		} else {
			createToast(context, str, theme, duration);
		}
		_toast.show();
	}


	private static void createToast(Context context, String str,
			ToastTheme theme, int duration) {
		if (theme == ToastTheme.SUCCESS) {
			_toast = DZTipsToast.makeText(context, str,
					R.drawable.agile_toast_success_icon, duration);

		} else if (theme == ToastTheme.ERROR) {
			_toast = DZTipsToast.makeText(context, str,
					R.drawable.agile_toast_error_icon, duration);

		} else if (theme == ToastTheme.SMILE) {
			_toast = DZTipsToast.makeText(context, str,
					R.drawable.agile_toast_smile_icon, duration);

		} else if (theme == ToastTheme.WARNING) {
			_toast = DZTipsToast.makeText(context, str,
					R.drawable.agile_toast_warning_icon, duration);

		}
	}

	public enum ToastTheme {
		SUCCESS, ERROR, SMILE, WARNING
	}

}

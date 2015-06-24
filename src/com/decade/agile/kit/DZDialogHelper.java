package com.decade.agile.kit;

import android.app.Activity;
import android.text.TextUtils;

import com.decade.agile.R;
import com.decade.agile.components.DZBaseDialogParams;
import com.decade.agile.components.DZDialog;
import com.decade.agile.components.DZRectDialog;
import com.decade.agile.components.DZStripDialog;

/**
 * @description: 对话框辅助类
 * @author: Decade
 * @date: 2013-6-14
 */
public class DZDialogHelper {
	public static DZDialog _dialog;

	/**
	 * 创建一个加载对话框
	 * 
	 * @param context
	 * @param content
	 */
	private static void createPrompt(Activity activity, String content,
			DialogTheme theme) {
		DZBaseDialogParams params = new DZBaseDialogParams();
		if (theme == DialogTheme.STRIP) {
			if (TextUtils.isEmpty(content)) {
				params.setContent(activity.getString(R.string.data_loading));
			} else {
				params.setContent(content);
			}
			_dialog = DZStripDialog.getInstance(activity, params);
		} else if (theme == DialogTheme.RECT) {
			if (TextUtils.isEmpty(content)) {
				params.setContent(activity
						.getString(R.string.agile_load_msg_ing));
			} else {
				params.setContent(content);
			}
			_dialog = DZRectDialog.getInstance(activity, params);
		}
	}

	/**
	 * 打开加载对话框
	 * 
	 * @param context
	 */
	public static void openPrompt(Activity activity, DialogTheme theme) {
		openPrompt(activity, null, theme);
	}

	public static void openPrompt(Activity activity, String content,
			DialogTheme theme) {
		createPrompt(activity, content, theme);
		openPrompt();
	}
	
	/**
	 * 打开加载对话框
	 */
	public static void openPrompt() {
		if (_dialog != null) {
			_dialog.show();
		}
	}

	/**
	 * 关闭加载对话框
	 */
	public static void closePrompt() {
		if (_dialog != null) {
			_dialog.dismiss();
		}
	}

	public enum DialogTheme {
		RECT, STRIP
	}

}

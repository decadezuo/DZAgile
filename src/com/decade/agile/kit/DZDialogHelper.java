package com.decade.agile.kit;

import android.content.Context;
import android.text.TextUtils;

import com.decade.agile.R;
import com.decade.agile.components.DZBaseDialogParams;
import com.decade.agile.components.DZRectDialog;
import com.decade.agile.components.DZStripDialog;
import com.decade.agile.components.DZiDialog;

/**
 * @description: 对话框辅助类
 * @author: Decade
 * @date: 2013-6-14
 */
public class DZDialogHelper {
	public static DZiDialog _dialog;

	/**
	 * 创建一个加载对话框
	 * 
	 * @param context
	 * @param content
	 */
	private static void createPrompt(Context context, String content,
			DialogTheme theme) {
		DZBaseDialogParams params = new DZBaseDialogParams();
		if (theme == DialogTheme.STRIP) {
			if (TextUtils.isEmpty(content)) {
				params.setContent(context.getString(R.string.data_loading));
			} else {
				params.setContent(content);
			}
			_dialog = DZStripDialog.getInstance(context, params);
		} else if (theme == DialogTheme.RECT) {
			if (TextUtils.isEmpty(content)) {
				params.setContent(context
						.getString(R.string.agile_load_msg_ing));
			} else {
				params.setContent(content);
			}
			_dialog = DZRectDialog.getInstance(context, params);
		}
	}

	/**
	 * 打开加载对话框
	 * 
	 * @param context
	 */
	public static void openPrompt(Context context, DialogTheme theme) {
		openPrompt(context, null, theme);
	}

	public static void openPrompt(Context context, String content,
			DialogTheme theme) {
		createPrompt(context, content, theme);
		openPrompt();
	}
	
	/**
	 * 打开加载对话框
	 */
	public static void openPrompt() {
		if (_dialog != null) {
			_dialog.open();
		}
	}

	/**
	 * 关闭加载对话框
	 */
	public static void closePrompt() {
		if (_dialog != null) {
			_dialog.close();
		}
	}

	public enum DialogTheme {
		RECT, STRIP
	}

}

package com.decade.agile.components;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

public abstract class DZDialog extends Dialog implements DZiDialog {

	public DZDialog(Context context) {
		super(context);
	}

	public DZDialog(Context context, int theme) {
		super(context, theme);
	}

	public void open() {
		if(!isShowing()){
			show();
		}
	}

	public void close() {
		if(isShowing()){
			dismiss();
		}
	}

	public enum Position {
		LEFT, CENTER, RIGHT
	}

	/**
	 * @description: 对话框按钮点击回调事件
	 * @author: Decade
	 * @date: 2013-9-16
	 * 
	 */
	public interface PromptBtnCallback{
		public void onClick(DZiDialog dialog, View view,
				Position position, int eventCode);
	}

}

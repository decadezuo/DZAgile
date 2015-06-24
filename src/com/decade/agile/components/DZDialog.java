package com.decade.agile.components;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.decade.agile.R;

public abstract class DZDialog extends DialogFragment {

	protected Activity _activity;

	public DZDialog(Activity activity) {
		_activity = activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, R.style.custom_dialog_base_style);
	}
	
	public void show(String tag) {
		super.show(((FragmentActivity)_activity).getSupportFragmentManager(), tag);
	}

	public void show() {
		super.show(((FragmentActivity)_activity).getSupportFragmentManager(), "");
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
	public interface PromptBtnCallback {
		public void onClick(DZDialog dialog, View view, Position position,
				int eventCode);
	}

}

package com.decade.agile.components;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/**
 * @description: 获得光标后Hint消失监控
 * @author: Decade
 * @date: 2014-12-18
 */
public class DZEditHintListener implements OnFocusChangeListener {

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		EditText _v = (EditText) v;
		if (!hasFocus) {// 失去焦点
			_v.setHint(_v.getTag().toString());
		} else {
			String hint = _v.getHint().toString();
			_v.setTag(hint);
			_v.setHint("");
		}
	}

	public static void addEditHintListener(EditText... params) {
		int size = params.length;
		for (int i = 0; i < size; i++) {
			params[i].setOnFocusChangeListener(new DZEditHintListener());
		}
	}

}

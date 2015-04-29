package com.decade.agile.kit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.decade.framework.kit.DZLog;

/**
 * 根据指定正则表达式过滤输入字符,注：匹配的字符会被过滤掉。
 * 
 * @description:
 * @author: Decade
 * @date: 2013-9-12
 * 
 */
public class DZFilterWatcher implements TextWatcher {
	private EditText _editText;
	private String _regEx;
	public DZFilterWatcher(EditText editText, String regEx) {
		_editText = editText;
		_regEx = regEx;
	}


	public void onTextChanged(CharSequence ss, int start, int before, int count) {
		String editable = _editText.getText().toString();
		String str = stringFilter(editable);
		if (str != null) {
			if (!editable.equals(str)) {
				_editText.setText(str);
				// 设置新的光标所在位置
				_editText.setSelection(str.length());
			}
		} else {
			DZLog.d(getClass(), "正则表达式错误");
		}

	}


	public void afterTextChanged(Editable s) {
	}


	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	private String stringFilter(String str) {
		try {
			String newStr = DZStringUtils.trim(str);
			Pattern p = Pattern.compile(_regEx);
			Matcher m = p.matcher(newStr);
			return m.replaceAll("").trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

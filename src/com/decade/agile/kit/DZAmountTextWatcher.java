package com.decade.agile.kit;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.decade.agile.components.DZToast;

/**
 * @description: 文字数量监听
 * @author: Decade
 * @date: 2013-6-14
 * 
 */
public class DZAmountTextWatcher implements TextWatcher {
	private CharSequence temp;
	private int editStart;
	private int editEnd;
	private EditText myEditText;
	private TextView prompt_tv;
	private Context context;
	private int limitWordsSum;
	private String mPromptString;

	/**
	 * @param context
	 * @param edit
	 *            监听的输入框
	 * @param promptTv
	 *            提示剩余文字TextView ，如果不需要实时提示可传入null
	 * @param promptstr
	 *            超过限制字数时的提示文字
	 * @param limitWordsSum
	 *            限制的字数
	 */
	public DZAmountTextWatcher(Context context, EditText edit,
			TextView promptTv, String promptstr, int limitWordsSum) {

		myEditText = edit;
		prompt_tv = promptTv;
		mPromptString = promptstr;
		this.context = context;
		this.limitWordsSum = limitWordsSum;
	}


	public void onTextChanged(CharSequence s, int start, int before, int count) {

		temp = s;
	}


	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	public void afterTextChanged(Editable s) {

		editStart = myEditText.getSelectionStart();
		editEnd = myEditText.getSelectionEnd();
		int text_count = limitWordsSum - temp.length();
		if (prompt_tv != null) {
			prompt_tv.setText("你还可以输入" + text_count + "个字");
		}
		if (temp.length() > limitWordsSum) {
			DZToast.showToastShort(context, mPromptString);
			s.delete(editStart - 1, editEnd);
			int tempSelection = editStart;
			myEditText.setSelection(tempSelection);
		}
	}
}

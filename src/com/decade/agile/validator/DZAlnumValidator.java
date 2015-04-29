package com.decade.agile.validator;

import java.util.regex.Pattern;

import android.content.Context;

/**
 * @description: 只允许输入字母+数字
 * @author: Decade
 * @date: 2013-6-24
 */
public class DZAlnumValidator extends DZAbstractValidator {

	/**
	 * This si Alnum Pattern to verify value.
	 */
	private static final Pattern mPattern = Pattern.compile("^[A-Za-z0-9]+$");

	private int _errorMessage;

	public DZAlnumValidator(Context c, int errorMessage) {
		super(c);
		_errorMessage = errorMessage;
	}

	@Override
	public boolean isValid(String value) {
		return mPattern.matcher(value).matches();
	}

	@Override
	public String getMessage() {
		return _context.getString(_errorMessage);
	}
}

package com.decade.agile.validator;

import java.util.regex.Pattern;

import android.content.Context;

/**
 * @description: 根据指定正则表达式验证
 * @author: Decade
 * @date: 2013-6-24
 */
public class DZRegExpValidator extends DZAbstractValidator {

	private Pattern mPattern;
	private int _errorMessage;

	public DZRegExpValidator(Context c, int errorMessage) {
		super(c);
		_errorMessage = errorMessage;
	}

	public void setPattern(String pattern) {
		mPattern = Pattern.compile(pattern);
	}

	@Override
	public boolean isValid(String value) throws DZValidatorException {
		if (mPattern != null) {
			return mPattern.matcher(value).matches();
		} else {
			throw new DZValidatorException("You can set Regexp Pattern first");
		}
	}

	@Override
	public String getMessage() {
		return _context.getString(_errorMessage);
	}
}

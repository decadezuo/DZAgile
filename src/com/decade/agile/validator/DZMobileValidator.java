package com.decade.agile.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;

import com.decade.agile.R;

public class DZMobileValidator extends DZAbstractValidator {

	private int _errorMessage;
	private int _emptyMsg;
	private int _formErrorMsg;

	public DZMobileValidator(Context c) {
		super(c);
		_errorMessage = R.string.validator_phone_error;
	}

	public DZMobileValidator(Context c, int emptyMsg, int formErrorMsg) {
		super(c);
		_emptyMsg = emptyMsg;
		_formErrorMsg = formErrorMsg;
	}

	@Override
	public boolean isValid(String charseq) {
		return validateMobile(charseq);
	}

	public boolean validateMobile(String phone) {
		int l = phone.length();
		boolean rs = false;
		switch (l) {
		case 0:
			if (_emptyMsg == 0) {
				_errorMessage = R.string.validator_phone_isempty;
			} else {
				_errorMessage = _emptyMsg;
			}
			rs = false;
			break;
		case 7:
			if (matchingText("^(13[0-9]|15[0-9]|18[7|8|9|6|5])\\d{4}$", phone)) {
				rs = true;
			} else {
				rs = false;
				setFormErrorMsg();
			}
			break;
		case 11:
			if (matchingText("^(13[0-9]|15[0-9]|18[7|8|9|6|5]|145)\\d{4,8}$",
					phone)) {
				rs = true;
			} else {
				rs = false;
				setFormErrorMsg();
			}
			break;
		default:
			setFormErrorMsg();
			rs = false;
			break;
		}
		return rs;
	}

	private void setFormErrorMsg() {
		if (_formErrorMsg == 0) {
			_errorMessage = R.string.validator_phone_error;
		} else {
			_errorMessage = _formErrorMsg;
		}
	}

	private boolean matchingText(String expression, String text) {
		Pattern p = Pattern.compile(expression); // 正则表达式
		Matcher m = p.matcher(text); // 操作的字符串
		boolean b = m.matches();
		return b;
	}

	@Override
	public String getMessage() {
		return _context.getResources().getString(_errorMessage);
	}

}

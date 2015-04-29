package com.decade.agile.validator;

import android.content.Context;

public class DZNotEmptyValidator extends DZAbstractValidator {
	
	private int _errorMessage;
	
	
	public DZNotEmptyValidator(Context c,int errorMessage) {
		super(c);
		_errorMessage = errorMessage;
	}
	
	@Override
	public boolean isValid(String value) {
		if(value != null){
			if(value.length() > 0)
				return true;
			else
				return false;
		}else{
			return false;
		}
	}

	@Override
	public String getMessage() {
		return _context.getString(_errorMessage);
	}

}

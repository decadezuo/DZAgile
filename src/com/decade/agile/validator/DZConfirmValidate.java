package com.decade.agile.validator;

import android.content.Context;
import android.widget.TextView;

public class DZConfirmValidate extends DZAbstractValidate {

	private TextView _field1;
	private TextView _field2;
	private Context _context;
	private TextView _source;
	private int _errorMessage;
	
	public DZConfirmValidate(Context context,TextView field1, TextView field2,int errorMessage){
		_field1 = field1;
		_field2 = field2;
		_errorMessage = errorMessage;
		_source = _field2;
		_context = context;
	}

	@Override
	public boolean isValid(String value) {
		if(_field1.getText().toString().length() > 0 && _field1.getText().toString().equals(_field2.getText().toString())){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public String getMessages() {
		return _context.getString(_errorMessage);
	}


	@Override
	public void addValidator(DZAbstractValidator validator) {
	}

	@Override
	public TextView getSource() {
		return _source;
	}
	
	
}

package com.decade.agile.validator;

import android.content.Context;
import android.widget.EditText;

/**
 * 
 * @description: 表单验证 工具类
 * @author: Decade
 * @date: 2013-9-12
 * 
 */
public class DZFormValidate {
	private Context _context;
	private DZForm _form;

	public DZFormValidate(Context context) {
		_context = context;
		_form = new DZForm();
	}

	/**
	 * 手机号码验证
	 * 
	 * @param editText
	 */
	public void addMobileValidator(EditText editText, int emptyMsg,
			int formErrorMsg) {
		DZMobileValidator validator = null;
		DZValidate validate = new DZValidate(editText);
		if (emptyMsg == 0 && formErrorMsg == 0) {
			validator = new DZMobileValidator(_context);
		} else {
			validator = new DZMobileValidator(_context, emptyMsg, formErrorMsg);
		}
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 非空验证
	 * 
	 * @param editText
	 * @param promptResId
	 */
	public void addNotEmptyValidator(EditText edit, int promptResId) {
		DZValidate validate = new DZValidate(edit);
		DZNotEmptyValidator validator = new DZNotEmptyValidator(_context,
				promptResId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 二次确认验证
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param emptyMsgId
	 * @param errorMsgId
	 */
	public void addConfirmValidate(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DZConfirmValidate validate = new DZConfirmValidate(_context, edit,
				confirmEdit, errorMsgId);
		_form.addValidates(validate);
	}

	/**
	 * 只允许输入字符+数字
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param errorMsgId
	 */
	public void addAlnumValidator(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DZValidate validate = new DZValidate(edit);
		DZAlnumValidator validator = new DZAlnumValidator(_context, errorMsgId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 邮箱验证
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param errorMsgId
	 */
	public void addEmailValidator(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DZValidate validate = new DZValidate(edit);
		DZEmailValidator validator = new DZEmailValidator(_context, errorMsgId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * URL验证
	 * 
	 * @param edit
	 * @param confirmEdit
	 * @param errorMsgId
	 */
	public void addUrlValidator(EditText edit, EditText confirmEdit,
			int errorMsgId) {
		DZValidate validate = new DZValidate(edit);
		DZUrlValidator validator = new DZUrlValidator(_context, errorMsgId);
		validate.addValidator(validator);
		_form.addValidates(validate);
	}

	/**
	 * 使用正则表达式验证
	 * 
	 * @param editText
	 * @param pattern
	 *            正则表达式
	 * @param promptResId
	 */
	public void add(EditText editText, String pattern, int promptResId) {
		DZValidate validate = new DZValidate(editText);
		DZRegExpValidator regExpValidator = new DZRegExpValidator(_context,
				promptResId);
		regExpValidator.setPattern(pattern);
		validate.addValidator(regExpValidator);
		_form.addValidates(validate);
	}

	/**
	 * 表单验证
	 * 
	 * @return true 表示通过，false 不通过
	 */
	public boolean isValidate() {
		return _form.validate();
	}

	public DZForm getForm() {
		return _form;
	}

}

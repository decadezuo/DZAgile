package com.decade.agile.validator;


import android.widget.TextView;

public abstract class DZAbstractValidate {

	/**
	 * Add a new validator for fields attached
	 * @param validator
	 * 		{@link DZAbstractValidator} : The validator to attach
	 */
	public abstract void addValidator(DZAbstractValidator validator);
	
	/**
	 * Function called when the {@link DZForm} validation
	 * @param value
	 * 		{@link String} : value to validate
	 * @return
	 * 		true if all validators are valid
     *      false if a validator is invalid
	 */
	public abstract boolean isValid(String value);
	
	/**
	 * Returns the error message displayed on the connected component
	 * @return
	 * 		{@link String} : the message to display
	 */
	public abstract String getMessages();
	
	/**
	 * Function recovering the field attached to our validator
	 * @return
	 * 		{@link TextView} : The fields attached
	 */
	public abstract TextView getSource();
}

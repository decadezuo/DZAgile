package com.decade.agile.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;

/**
 * @description: 邮箱验证
 * @author: Decade
 * @date: 2013-6-24
 */
public class DZEmailValidator extends DZAbstractValidator {
	
	private String mDomainName = "";
	private int _errorMessage;

	public DZEmailValidator(Context c, int errorMessage) {
		super(c);
		_errorMessage = errorMessage;
	}
	
	@Override
	public boolean isValid(String charseq) {
		if(charseq.length() > 0 ){
			boolean matchFound = false;
			
			//Input the string for validation
			String email = charseq.toString();
			 
			if(mDomainName != null && mDomainName.length() > 0){
				//Test avec le domaine
				
			    //Set the email pattern string
			    Pattern p = Pattern.compile(".+@"+mDomainName);
			    //Match the given string with the pattern
			    Matcher m = p.matcher(email);
			    //check whether match is found 
			    matchFound = m.matches();
		
			    if (matchFound)
			        return true;
			    else
			        return false;
			}else{
				//test sans le domaine
				
			    //Set the email pattern string
			    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			    //Match the given string with the pattern
			    Matcher m = p.matcher(email);
			    //check whether match is found 
			    matchFound = m.matches();
			}
			
			if (matchFound)
		        return true;
		    else
		        return false;
		}else{
			return true;
		}
	}
	
	@Override
	public String getMessage() {
		return _context.getString(_errorMessage);
	}
	
	/**
	 * Lets say that the email address must be valid for such domain.
     * This function only accepts strings of Regexp
	 * @param name Regexp Domain Name
	 * 
	 * example : gmail.com
	 */
	public void setDomainName(String name){
		mDomainName = name;
	}
}

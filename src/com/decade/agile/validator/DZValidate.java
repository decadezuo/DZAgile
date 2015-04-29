package com.decade.agile.validator;

import java.util.ArrayList;
import java.util.Iterator;

import android.widget.TextView;

/**
 * @description: 表单验证器
 * @author: Decade
 * @date: 2013-9-16
 */
public class DZValidate extends DZAbstractValidate{

	/**
     * Validator chain
     */
    protected ArrayList<DZAbstractValidator> _validators = new ArrayList<DZAbstractValidator>();
    
    /**
     * Validation failure messages
     */
    protected String _message = new String();
    
    /**
     * 
     */
    protected TextView _source;
    
    
    public DZValidate(TextView source){
    	this._source = source;
    }

    /**
     * Adds a validator to the end of the chain
     *
     * @param validator
     */
    public void addValidator(DZAbstractValidator validator)
    {
    	this._validators.add(validator);
    	return;
    }
    
    public boolean isValid(String value){
    	boolean result = true;
    	this._message = new String();
    	
    	Iterator<DZAbstractValidator> it = this._validators.iterator();
    	while(it.hasNext()){
    		DZAbstractValidator validator = it.next();
            try{
                if(!validator.isValid(value)){
                    this._message = validator.getMessage();
                    result = false;
                    break;
                }
            }catch(DZValidatorException e){
                System.err.println(e.getMessage());
                System.err.println(e.getStackTrace());
                this._message = e.getMessage();
                result = false;
                break;
            }
    	}
    	
    	return result;
    }
    
    public String getMessages(){
    	return this._message;
    }
    
    public TextView getSource(){
    	return this._source;
    }
    
}

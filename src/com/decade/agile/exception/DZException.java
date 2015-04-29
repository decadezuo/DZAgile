package com.decade.agile.exception;

/**
 * @description: 
 * @author: Decade
 * @date: 2013-5-31
 * 
 */
public class DZException extends Exception{
	private static final long serialVersionUID = 1L;

	public DZException() {
		super();
	}

	public DZException(String msg) {
		super(msg);
	}
	
	public DZException(Throwable cause) {
		super(cause);
	}
	
	public DZException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DZException(int code, Throwable cause){
		super(cause);
	}
	
	public DZException(int code,String msg) {
		super(msg);
	}
	
	public DZException(int code,String msg, Throwable cause) {
		super(msg, cause);
	}
}

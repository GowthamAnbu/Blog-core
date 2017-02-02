package com.gowtham.exception;

public class ValidationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String message,Throwable e) {
		super(message,e);
	}
	
	public ValidationException(String message) {
		super(message);
	}
}

package com.cg.fms.exception;

public class CustomerException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public CustomerException(String errorMessage) {
		super(errorMessage);
	}
}



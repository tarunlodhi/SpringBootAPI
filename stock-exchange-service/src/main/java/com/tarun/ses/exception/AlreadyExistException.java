package com.tarun.ses.exception;

public class AlreadyExistException extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public AlreadyExistException(String name) {
		super(name);
	}
}

package com.tarun.sms.exception;

import com.tarun.sms.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class AlreadyExistException extends RuntimeException {

	public AlreadyExistException(String name) {
		super(name);
	}
}

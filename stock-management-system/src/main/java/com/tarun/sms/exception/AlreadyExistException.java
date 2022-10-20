package com.tarun.sms.exception;

import com.tarun.sms.ExcludeFromJacocoGeneratedReport;

@SuppressWarnings("serial")
@ExcludeFromJacocoGeneratedReport
public class AlreadyExistException extends RuntimeException {

	public AlreadyExistException(String name) {
		super(name);
	}
}

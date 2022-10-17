package com.tarun.sms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tarun.sms.ExcludeFromJacocoGeneratedReport;
import com.tarun.sms.exception.AlreadyExistException;

@ExcludeFromJacocoGeneratedReport
public class BaseController {
	/**
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public String exceptionHandlerExceptionResolver() {
		return "Please Provide All Entity in formate \r\n" + "{\r\n" + "  \"name\": \"string\",\r\n"
				+ "  \"turnover\": 0,\r\n" + "  \"ceo\": \"string\",\r\n" + "  \"status\": true,\r\n"
				+ "  \"directors\": [\r\n" + "    {\r\n" + "      \"id\": 0,\r\n" + "      \"name\": \"string\"\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"sector\": \"string\",\r\n" + "  \"briefDescription\": \"string\",\r\n"
				+ "  \"stockcodeNSE\": \"string\",\r\n" + "  \"stockcodeBSE\": 0\r\n" + "}";
	}

	// AlreadyExistException
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ AlreadyExistException.class })
	public String companyAlreadyExist() {
		return "The Company is already Exist";

	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ ConstraintViolationException.class })
	public String directorNameRequired() {
		return "Director Name Cant be empty";

	}
}

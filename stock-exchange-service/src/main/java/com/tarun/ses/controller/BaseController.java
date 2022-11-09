package com.tarun.ses.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tarun.ses.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class BaseController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> errors = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public String duplicateCompanyException() {
		return "IPO Already exists";
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public String dateAndTimeException() {
		return "Date need to be added in dd-MM-yyyy formate";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	public String alreadyExistException() {
		return "IPO doesnt exist";
	}
}
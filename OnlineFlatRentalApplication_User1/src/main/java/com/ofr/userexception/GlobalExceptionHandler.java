package com.ofr.userexception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RegisterationException.class)
	public ResponseEntity<Object> handleAdminRegistrationException(RegisterationException exception,
			WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(404);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

	}
	
	@ExceptionHandler(value = DuplicateUserRecordException.class)
	public ResponseEntity<Object> handleDuplicateRecordException(DuplicateUserRecordException exception,
			WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(406);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> handleDuplicateRecordException(UserNotFoundException exception,
			WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(406);
		exceptionResponse.setTime(LocalDateTime.now());
		exceptionResponse.setMessage(exception.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

	}
}

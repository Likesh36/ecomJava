package com.training.ecommerce.advice;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.training.ecommerce.exception.ErrorDetails;
import com.training.ecommerce.exception.UserAlreadyExistsException;
import com.training.ecommerce.exception.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandling extends ResponseEntityExceptionHandler {

	public ResponseEntity<ErrorDetails> handleException(UserNotFoundException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<ErrorDetails> handleException(UserAlreadyExistsException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(exception.getMessage(), LocalDate.now(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
	}

}

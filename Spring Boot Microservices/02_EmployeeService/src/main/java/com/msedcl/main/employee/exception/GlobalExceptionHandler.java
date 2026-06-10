package com.msedcl.main.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleGlobalException(Exception exception, WebRequest webRequest) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetail.setDetail("Opps");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
	}

}

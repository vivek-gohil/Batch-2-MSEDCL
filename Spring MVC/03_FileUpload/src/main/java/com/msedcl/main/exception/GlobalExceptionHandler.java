package com.msedcl.main.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e, Model model) {
		log.info("In Global Exception Handling");
		model.addAttribute("error", "File size exceeds the max allowed limit");
		return "file-upload";
	}

	@ExceptionHandler(MultipartException.class)
	public String handleException(MultipartException e, Model model) {

		log.info("In MultipartException General");
		model.addAttribute("error", e.getMessage());
		return "file-upload";
	}
}

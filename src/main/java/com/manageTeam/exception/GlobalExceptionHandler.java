package com.manageTeam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(GlobalException.class)
	protected ResponseEntity<ErrorDto> handleTesseractException(GlobalException e){
		
		e.printStackTrace();
		ErrorDto errorDto = new ErrorDto(e.getCode(), e.getMessage());
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorDto> handleTesseractException(Exception e){
		e.printStackTrace();
		ErrorDto errorDto = new ErrorDto("ERROR", "시스템에서 알수없는 에러가 발생했습니다.");
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

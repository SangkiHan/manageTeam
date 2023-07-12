package com.manageTeam.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @description API호출 중 발생 예외들을 처리하기 위한 ExceptionHandler
 * @author skhan
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * @description 커스텀한 GlobalException을 처리하는 Handler
	 * @author skhan
	 */
	@ExceptionHandler(GlobalException.class)
	protected ResponseEntity<ErrorDto> handleGlobalException(GlobalException e){
		e.printStackTrace();
		ErrorDto errorDto = new ErrorDto(e.getErrorCode());
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @description 커스텀한 GlobalException을 제외한 Exception을 처리하기 위한 Handler
	 * @author skhan
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorDto> handleException(Exception e){
		e.printStackTrace();
		ErrorDto errorDto = new ErrorDto("ERROR", "시스템에서 알수없는 에러가 발생했습니다.");
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

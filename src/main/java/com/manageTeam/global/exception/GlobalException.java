package com.manageTeam.global.exception;

import lombok.Getter;

/**
 * @description 에러 메시지, 에러코드를 커스텀하기 위한 커스텀 Exception.class
 * @author skhan
 * */
@Getter
public class GlobalException extends RuntimeException  {
	
	private static final long serialVersionUID = 8636904039967520807L;
	
	private ErrorCode errorCode;
	
	public GlobalException(ErrorCode errorCode, Exception e) {
		super(errorCode.getMessage(), e);
		this.errorCode = errorCode;
	}
	
	public GlobalException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}

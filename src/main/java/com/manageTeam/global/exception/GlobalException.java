package com.manageTeam.global.exception;

/**
 * @description 에러 메시지, 에러코드를 커스텀하기 위한 커스텀 Exception.class
 * @author skhan
 * */
public class GlobalException extends RuntimeException  {
	
	private static final long serialVersionUID = 8636904039967520807L;
	
	private String code;
	private String message;
	
	public GlobalException(String code, String message, Exception e) {
		this.code = code;
		this.message = message;
	}
	
	public GlobalException(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public GlobalException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		if(message == null || message.length() == 0) {
				return "요청처리에 실패하였습니다.";
		}
		return message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}

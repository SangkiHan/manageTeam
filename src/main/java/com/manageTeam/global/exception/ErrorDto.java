package com.manageTeam.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * @description 에러메시지 통일을 위한 DTO
 * @author skhan
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDto {

	private String errorCode;
	private String errorMessage;
	public ErrorDto(ErrorCode errorCode) {
		this.errorMessage = errorCode.getMessage();
		this.errorCode = errorCode.getCode();
	}
}


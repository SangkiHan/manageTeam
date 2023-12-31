package com.manageTeam.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	//User
	USER_UNKNOWN("USR0001", "해당 사용자가 존재하지 않습니다. 관리자에게 문의 해주세요"),
	USER_EXIST("USR0002", "이미 해당 주민번호로 등록된 팀원이 존재합니다."),
	
	//TEAM
	TEAM_UNKNOWN("TEA0001","팀이 존재하지않습니다. 관리자에게 문의 해주세요"),
	TEAM_NUMBER("TEA0002","팀 인원이 5명 미만입니다."),
	TEAM_MANAGER("TEA0003","팀의 관리자가 존재 하지않습니다."),
	TEAM_EXIST("TEA0004", "팀이 이미 존재합니다."),
	
	//MEMBER
	MEMBER_UNKNOWN("MEM0001","팀원이 존재하지 않습니다."),
	
	//RESERVATION
	RESERVATION_UNKNOWN("RET0001","해당 예약이 존재하지 않습니다."),
	RESERVATION_END("RET0002", "해당 예약은 마감되었습니다."),
	RESERVATION_TIME("RET0003", "해당 시간에 이미 예약된 건이 존재합니다."),
	
	//GYM
	GYM_UNKNOWN("GYM0001","체육관이 존재하지않습니다."),
	GYM_EXIST("GYM0001","이미 존재하는 체육관입니다."),
	
	//COMPETITION
	COMPETITION_UNKNOWN("COP0001","대회가 존재하지 않습니다."),
	COMPETITION_DATE("COP0002","대회가 해당 시간에 이미 등록되어있습니다."),
	COMPETITION_DEADLINE("COP0003","이미 마감된 대회입니다."),
	
	//COMPETITION_TEAM
	COMPETITION_TEAM_UNKNOWN("COT0001","대회가 존재하지 않습니다."),
	COMPETITION_TEAM_DATE("COT0002","해당 날짜에 대회가 이미 등록되어있습니다."),
	
	//AES
	ENCRYPT_ERROR("ENC0001", "암호화 중 에러가 발생하였습니다."),
	DECRYPT_ERROR("DEC0001", "복호화 중 에러가 발생하였습니다."),
	
	//SECURITY
	PASSWORD_ERROR("PAS0001", "비밀번호가 일치하지 않습니다.");
	
	private String code;
	private String message;
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
	
	
	

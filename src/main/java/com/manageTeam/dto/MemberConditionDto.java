package com.manageTeam.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel(value = "팀원 검색조건 Dto")
@Getter
public class MemberConditionDto {

	/**
	 * 팀명
	 */
	private String teamName;
	/**
	 * 선수명
	 */
	private String memberName;
	/**
	 * 나이
	 */
	private int ageGoe;
	/**
	 * 나이
	 */
	private int ageLoe;
	/**
	 * 도시
	 */
	private String city;

}

package com.manageTeam.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel(value = "팀원 검색조건 Dto")
@Getter
public class MemberConditionDto {

	private String teamName;
	private String memberName;
	private int ageGoe;
	private int ageLoe;
	private String city;

}

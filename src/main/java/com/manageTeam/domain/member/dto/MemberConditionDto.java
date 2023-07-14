package com.manageTeam.domain.member.dto;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "팀원 검색조건 Dto")
@Getter
@Setter
public class MemberConditionDto {

	@ApiParam(value = "팀명")
	private String teamName;
	
	@ApiParam(value = "선수명")
	private String memberName;
	
	@ApiParam(value = "나이 <=")
	private int ageGoe;
	
	@ApiParam(value = "나이 >=")
	private int ageLoe;
	
	@ApiParam(value = "도시")
	private String city;
	
	@ApiParam(value = "활성화 상태")
	private ActivateStatus activateStatus;

}

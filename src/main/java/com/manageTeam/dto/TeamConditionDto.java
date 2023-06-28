package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel(value = "팀원 검색조건 Dto")
@Getter
public class TeamConditionDto {
	private String teamName;
	private String city;
	private ActivateStatus activateStatus;
}

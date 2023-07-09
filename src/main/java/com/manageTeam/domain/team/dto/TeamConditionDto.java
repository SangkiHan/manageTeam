package com.manageTeam.domain.team.dto;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "팀 검색조건 Dto")
@Getter
@Setter
public class TeamConditionDto {
	/**
	 * 팀명
	 */
	private String teamName;
	/**
	 * 도시
	 */
	private String city;
	/**
	 * 활성화상태
	 */
	private ActivateStatus activateStatus;
}

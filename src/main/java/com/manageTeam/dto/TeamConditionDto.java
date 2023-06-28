package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;

import lombok.Getter;

@Getter
public class TeamConditionDto {
	private String teamName;
	private String city;
	private ActivateStatus activateStatus;
}

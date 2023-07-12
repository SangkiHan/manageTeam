package com.manageTeam.domain.competitionTeam.dto;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CompetitionTeamDto {
	@ApiModel(value = "CompetitionTeamId Dto")
	@Getter
	@RequiredArgsConstructor
	public static class CompetitionTeamId{
		private Long competitionTeamId;
	}
	
	
	@ApiModel(value = "대회 참가 Dto")
	@Getter
	@RequiredArgsConstructor
	public static class Save{
		private Long competitionTeamId;
		private Long teamId;
		private Long competitionId;
		private ActivateStatus activateStatus;
	}
	
}

package com.manageTeam.domain.competitionTeam.dto;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CompetitionTeamDto {
	@ApiModel(value = "CompetitionTeamId Dto")
	@Getter
	@NoArgsConstructor
	public static class CompetitionTeamId{
		@ApiParam(value = "대회참가ID")
		private Long competitionTeamId;
	}
	
	
	@ApiModel(value = "대회 참가 Dto")
	@Getter
	@NoArgsConstructor
	public static class Save{
		@ApiParam(value = "대회참가ID")
		private Long competitionTeamId;
		@ApiParam(value = "팀ID")
		private Long teamId;
		@ApiParam(value = "대회ID")
		private Long competitionId;
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
	}
	
}

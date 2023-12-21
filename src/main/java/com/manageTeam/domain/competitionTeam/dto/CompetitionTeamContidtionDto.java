package com.manageTeam.domain.competitionTeam.dto;

import java.sql.Date;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

public class CompetitionTeamContidtionDto {
	
	@Getter
	public static class checkDate{
		
		@ApiParam(value = "팀 ID")
		private Long teamId;
		
		@ApiParam(value = "시작 날짜 <=")
		private Date startDate;
		
		@ApiParam(value = "날짜 >=")
		private Date endDate;

		@Builder
		private checkDate(Long teamId, Date startDate, Date endDate) {
			this.teamId = teamId;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
}

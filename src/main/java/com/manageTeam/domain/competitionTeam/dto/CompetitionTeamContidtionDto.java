package com.manageTeam.domain.competitionTeam.dto;

import java.sql.Date;

import lombok.Getter;

public class CompetitionTeamContidtionDto {
	
	@Getter
	public static class checkDate{
		/**
		 * TeamId
		 */
		private Long teamId;
		/**
		 * 날짜 <=
		 */
		private Date startDate;
		/**
		 * 날짜 >=
		 */
		private Date endDate;
		
		public checkDate(Long teamId, Date startDate, Date endDate) {
			this.teamId = teamId;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
}

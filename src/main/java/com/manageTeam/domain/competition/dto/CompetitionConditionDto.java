package com.manageTeam.domain.competition.dto;

import java.sql.Date;

import lombok.Getter;

public class CompetitionConditionDto {
	
	@Getter
	public static class DateCheck{
		/**
		 * 체육관ID
		 */
		private Long gymId;
		/**
		 * 시작 날짜
		 */
		private Date startDate;
		/**
		 * 종료 날짜
		 */
		private Date endDate;
		
		public DateCheck(CompetitionDto.Save competition) {
			super();
			this.gymId = competition.getGymId();
			this.startDate = competition.getStartDate();
			this.endDate = competition.getEndDate();
		}
	}
}

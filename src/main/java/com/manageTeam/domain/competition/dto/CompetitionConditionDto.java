package com.manageTeam.domain.competition.dto;

import java.sql.Date;

import com.manageTeam.global.entity.ActivateStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionConditionDto {
	/**
	 * 시작 날짜
	 */
	private Date startDate;
	/**
	 * 종료 날짜
	 */
	private Date endDate;
	/**
	 * 활성화 상태
	 */
	private ActivateStatus activateStatus;
	/**
	 * 개최지
	 */
	private String city;
	
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

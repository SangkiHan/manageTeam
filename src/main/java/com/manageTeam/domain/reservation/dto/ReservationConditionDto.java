package com.manageTeam.domain.reservation.dto;

import java.time.LocalDateTime;

import com.manageTeam.entity.ActivateStatus;

import lombok.Getter;


public class ReservationConditionDto {
	
	@Getter
	public static class ListCondition{
		/**
		 * 체육관명
		 */
		private String gymName;
		/**
		 * 날짜 >=
		 */
		private LocalDateTime dateGoe;
		/**
		 * 날짜 <=
		 */
		private LocalDateTime dateLoe;
		/**
		 * 도시
		 */
		private String city;
		/**
		 * 활성화상태
		 */
		private ActivateStatus activateStatus;
		
		public ListCondition(String gymName, LocalDateTime dateGoe, LocalDateTime dateLoe, String city) {
			this.gymName = gymName;
			this.dateGoe = dateGoe;
			this.dateLoe = dateLoe;
			this.city = city;
		}
	}
	
	@Getter
	public static class DateCondition{
		/**
		 * 시작날짜
		 */
		private LocalDateTime startDate;
		/**
		 * 종료날짜
		 */
		private LocalDateTime endDate;
		
		public DateCondition(LocalDateTime startDate, LocalDateTime endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
}

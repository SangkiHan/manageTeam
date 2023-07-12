package com.manageTeam.domain.reservation.dto;

import java.time.LocalDateTime;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;


public class ReservationConditionDto {
	
	@ApiModel(value = "체육관 예약 목록 조회 조건 Dto")
	@Getter
	public static class ListCondition{
		/**
		 * 체육관명
		 */
		private String gymName;
		/**
		 * 날짜 <=
		 */
		private LocalDateTime startDate;
		/**
		 * 날짜 >=
		 */
		private LocalDateTime endDate;
		/**
		 * 도시
		 */
		private String city;
		/**
		 * 활성화상태
		 */
		private ActivateStatus activateStatus;
		
		public ListCondition(String gymName, LocalDateTime startDate, LocalDateTime endDate, String city) {
			this.gymName = gymName;
			this.startDate = startDate;
			this.endDate = endDate;
			this.city = city;
		}
	}
	
	@ApiModel(value = "체육관 예약 등록일 조회 Dto")
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

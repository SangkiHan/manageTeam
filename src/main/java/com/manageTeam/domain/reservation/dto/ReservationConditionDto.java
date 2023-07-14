package com.manageTeam.domain.reservation.dto;

import java.time.LocalDateTime;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;


public class ReservationConditionDto {
	
	@ApiModel(value = "체육관 예약 목록 조회 조건 Dto")
	@Getter
	public static class ListCondition{
		
		@ApiParam(value = "체육관명")
		private String gymName;
		
		@ApiParam(value = "날짜 <=")
		private LocalDateTime startDate;
		
		@ApiParam(value = "날짜 >=")
		private LocalDateTime endDate;
		
		@ApiParam(value = "도시")
		private String city;
		
		@ApiParam(value = "활성화상태")
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
		
		@ApiParam(value = "시작날짜")
		private LocalDateTime startDate;
		
		@ApiParam(value = "종료날짜")
		private LocalDateTime endDate;
		
		public DateCondition(LocalDateTime startDate, LocalDateTime endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
}

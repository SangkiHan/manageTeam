package com.manageTeam.dto;

import java.time.LocalDateTime;

import lombok.Getter;


public class ReservationConditionDto {
	
	@Getter
	public static class ListCondition{
		private String gymName;
		private LocalDateTime dateGoe;
		private LocalDateTime dateLoe;
		private String city;
		
		public ListCondition(String gymName, LocalDateTime dateGoe, LocalDateTime dateLoe, String city) {
			this.gymName = gymName;
			this.dateGoe = dateGoe;
			this.dateLoe = dateLoe;
			this.city = city;
		}
	}
	
	@Getter
	public static class DateCondition{
		private LocalDateTime startDate;
		private LocalDateTime endDate;
	}
	
}

package com.manageTeam.dto;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ReservationConditionDto {
	private String gymName;
	private LocalDateTime dateGoe;
	private LocalDateTime dateLoe;
	private String city;
	
	public ReservationConditionDto(String gymName, LocalDateTime dateGoe, LocalDateTime dateLoe, String city) {
		this.gymName = gymName;
		this.dateGoe = dateGoe;
		this.dateLoe = dateLoe;
		this.city = city;
	}
}

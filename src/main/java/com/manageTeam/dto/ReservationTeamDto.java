package com.manageTeam.dto;

import lombok.Getter;

public class ReservationTeamDto {
	
	@Getter
	public static class Save{
		private Long reservationTeamId;
		private Long teamId;
		private Long reservationId;
	}

}

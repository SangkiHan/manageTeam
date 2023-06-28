package com.manageTeam.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

public class ReservationDto {
	
	@ApiModel(value = "예약 등록 Dto")
	@Getter
	public static class Save{
		private Long reservationId;
		private int totalTeamCnt;
		private Long gymId;
		private LocalDateTime startTime;
		private LocalDateTime endTime;
		
		public Save() {};
		
		public Save(int totalTeamCnt, Long gymId, LocalDateTime startTime, LocalDateTime endTime) {
			this.totalTeamCnt = totalTeamCnt;
			this.gymId = gymId;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
}

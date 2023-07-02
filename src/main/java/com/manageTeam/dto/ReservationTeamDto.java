package com.manageTeam.dto;

import java.time.LocalDateTime;

import com.manageTeam.entity.ActivateStatus;

import lombok.Getter;
import lombok.ToString;

public class ReservationTeamDto {
	
	@Getter
	public static class Save{
		private Long reservationTeamId;
		private Long teamId;
		private Long reservationId;
		private ActivateStatus activateStatus;
		
		public Save(Long teamId, Long reservationId) {
			this.teamId = teamId;
			this.reservationId = reservationId;
			this.activateStatus = ActivateStatus.YES;
		}
	}
	
	@Getter
	@ToString
	public static class Info{
		private Long reservationTeamId;
		private Long teamId;
		private Long reservationId;
		private Long gymId;
		private String gymName;
		private String city;
		private ActivateStatus activateStatus;
		private LocalDateTime startDate;
		private LocalDateTime endDate;
		
		public Info() {};
		
		public Info(Long reservationTeamId, Long teamId, Long reservationId, Long gymId, String gymName, String city,
				ActivateStatus activateStatus, LocalDateTime startDate, LocalDateTime endDate) {
			super();
			this.reservationTeamId = reservationTeamId;
			this.teamId = teamId;
			this.reservationId = reservationId;
			this.gymId = gymId;
			this.gymName = gymName;
			this.city = city;
			this.activateStatus = activateStatus;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
}

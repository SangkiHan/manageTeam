package com.manageTeam.dto;

import java.time.LocalDateTime;

import com.manageTeam.entity.ActivateStatus;

import lombok.Getter;
import lombok.ToString;

public class ReservationTeamDto {
	
	@Getter
	public static class Save{
		/**
		 * 예약 팀 ID
		 */
		private Long reservationTeamId;
		private Long teamId;
		/**
		 * 팀ID
		 */
		private Long reservationId;
		/**
		 * 활성화 상태
		 */
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
		/**
		 * 예약 팀 ID
		 */
		private Long reservationTeamId;
		/**
		 * 팀 ID
		 */
		private Long teamId;
		/**
		 * 예약 ID
		 */
		private Long reservationId;
		/**
		 * 체육관 ID
		 */
		private Long gymId;
		/**
		 * 체육관 이름
		 */
		private String gymName;
		/**
		 * 도시
		 */
		private String city;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		/**
		 * 예약 시작날짜
		 */
		private LocalDateTime startDate;
		/**
		 * 예약 종료날짜
		 */
		private LocalDateTime endDate;
		
		public Info() {};
		
		/**
		 * Entity to Dto Constructor
		 */
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

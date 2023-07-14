package com.manageTeam.domain.reservationTeam.dto;

import java.time.LocalDateTime;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationTeamDto {
	
	@ApiModel(value = "팀 체육관 예약 Dto")
	@Getter
	public static class Save{
		
		@ApiParam(value = "예약 팀ID")
		private Long reservationTeamId;
		
		@ApiParam(value = "팀ID")
		private Long teamId;
		
		@ApiParam(value = "예약ID")
		private Long reservationId;
		
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
		
		public Save(Long teamId, Long reservationId) {
			this.teamId = teamId;
			this.reservationId = reservationId;
			this.activateStatus = ActivateStatus.YES;
		}
	}
	
	@ApiModel(value = "팀 체육관 예약 목록 Dto")
	@Getter
	@NoArgsConstructor
	public static class Info{
		
		@ApiParam(value = "예약 팀 ID")
		private Long reservationTeamId;
		
		@ApiParam(value = "팀 ID")
		private Long teamId;
		
		@ApiParam(value = "예약 ID")
		private Long reservationId;
		
		@ApiParam(value = "체육관 ID")
		private Long gymId;
		
		@ApiParam(value = "체육관 이름")
		private String gymName;
		
		@ApiParam(value = "도시")
		private String city;
		
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
		
		@ApiParam(value = "예약 시작날짜")
		private LocalDateTime startDate;
		
		@ApiParam(value = "예약 종료날짜")
		private LocalDateTime endDate;
		
		/**
		 * Entity to Dto Constructor
		 */
		public Info(Long reservationTeamId, Long teamId, Long reservationId, Long gymId, String gymName, String city,
				ActivateStatus activateStatus, LocalDateTime startDate, LocalDateTime endDate) {
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

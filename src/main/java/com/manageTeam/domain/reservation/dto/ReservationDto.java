package com.manageTeam.domain.reservation.dto;

import java.time.LocalDateTime;

import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.Address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationDto {
	
	@ApiModel(value = "Id Dto")
	@Getter
	@NoArgsConstructor
	public static class Id{
		
		@ApiParam(value = "예약ID")
		private Long reservationId;
		
		public Id(Long reservationId) {
			this.reservationId = reservationId;
		}
	}
	
	@ApiModel(value = "예약 등록 Dto")
	@Getter
	@NoArgsConstructor
	public static class Save{
		
		@ApiParam(value = "예약ID")
		private Long reservationId;
		
		@ApiParam(value = "체육관 예약가능한 팀수")
		private int totalTeamCnt;
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "시작날짜")
		private LocalDateTime startTime;
		
		@ApiParam(value = "종료날짜")
		private LocalDateTime endTime;
		
		public Save(int totalTeamCnt, Long gymId, LocalDateTime startTime, LocalDateTime endTime) {
			this.totalTeamCnt = totalTeamCnt;
			this.gymId = gymId;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
	
	@ApiModel(value = "예약 목록 Dto")
	@Getter
	@NoArgsConstructor
	public static class Info{
		
		@ApiParam(value = "예약ID")
		private Long reservationId;
		
		@ApiParam(value = "체육관 예약가능한 팀수")
		private int totalTeamCnt;
		
		@ApiParam(value = "현재 예약한 팀의 수")
		private Long joinTeam;
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "체육관 이름")
		private String gymName;
		
		@ApiParam(value = "체육관 주소")
		private AddressDto address;
		
		@ApiParam(value = "예약 시작시작")
		private LocalDateTime startTime;
		
		@ApiParam(value = "예약 종료시간")
		private LocalDateTime endTime;
		
		/**
		 * Entity to Dto Constructor
		 */
		public Info(
				Long reservationId, 
				int totalTeamCnt, 
				Long joinTeam,
				Long gymId, 
				String gymName, 
				Address address,
				LocalDateTime startTime, 
				LocalDateTime endTime
				) {
			this.reservationId = reservationId;
			this.totalTeamCnt = totalTeamCnt;
			this.joinTeam = joinTeam;
			this.gymId = gymId;
			this.gymName = gymName;
			this.address = new AddressDto(address);
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
}

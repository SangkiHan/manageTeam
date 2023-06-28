package com.manageTeam.dto;

import java.time.LocalDateTime;

import com.manageTeam.entity.Address;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

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
	
	@ApiModel(value = "예약 목록 Dto")
	@Getter
	@ToString
	public static class Info{
		private Long reservationId;
		private int totalTeamCnt;
		private Long joinTeam;
		private Long gymId;
		private String gymName;
		private AddressDto gymAddress;
		private LocalDateTime startTime;
		private LocalDateTime endTime;
		
		public Info() {};
		
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
			this.gymAddress = new AddressDto(address);
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
}

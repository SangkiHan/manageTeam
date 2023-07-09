package com.manageTeam.domain.reservation.dto;

import java.time.LocalDateTime;

import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.Address;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

public class ReservationDto {
	
	@ApiModel(value = "Id Dto")
	@Getter
	public static class Id{
		/**
		 * 예약ID
		 */
		private Long reservationId;
		
		public Id() {};
		
		public Id(Long reservationId) {
			this.reservationId = reservationId;
		}
	}
	
	@ApiModel(value = "예약 등록 Dto")
	@Getter
	public static class Save{
		/**
		 * 예약ID
		 */
		private Long reservationId;
		/**
		 * 체육관 예약가능한 팀수
		 */
		private int totalTeamCnt;
		/**
		 * 체육관ID
		 */
		private Long gymId;
		/**
		 * 시작날짜
		 */
		private LocalDateTime startTime;
		/**
		 * 종료날짜
		 */
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
		/**
		 * 예약ID
		 */
		private Long reservationId;
		/**
		 * 체육관 예약가능한 팀수
		 */
		private int totalTeamCnt;
		/**
		 * 현재 예약한 팀의 수
		 */
		private Long joinTeam;
		/**
		 * 체육관ID
		 */
		private Long gymId;
		/**
		 * 체육관 이름
		 */
		private String gymName;
		/**
		 * 체육관 주소
		 */
		private AddressDto address;
		/**
		 * 예약 시작시작
		 */
		private LocalDateTime startTime;
		/**
		 * 예약 종료시간
		 */
		private LocalDateTime endTime;
		
		public Info() {};
		
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

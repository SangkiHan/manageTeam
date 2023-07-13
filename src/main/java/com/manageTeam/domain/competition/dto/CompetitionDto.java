package com.manageTeam.domain.competition.dto;

import java.sql.Date;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CompetitionDto {
	
	/**
	 * @description 대회를 등록 및 수정 DTO
	 * @author skhan
	 */
	@ApiModel(value = "대회 등록 Dto")
	@Getter
	@NoArgsConstructor
	public static class Save{
		/**
		 * 대회ID
		 */
		private Long competitionId;
		/**
		 * 대회 개최지
		 */
		private Long gymId;
		/**
		 * 대회에 참여 가능한 팀수
		 */
		private int teamCnt;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		/**
		 * 시작 날짜
		 */
		private Date startDate;
		/**
		 * 종료 날짜
		 */
		private Date endDate;
		
		/**
		 * 테스트용
		 */
		public Save(Long gymId, int teamCnt) {
			this.gymId = gymId;
			this.teamCnt = teamCnt;
		}
	}
	
	@Getter
	@Setter
	public static class Info{
		/**
		 * 대회ID
		 */
		private Long competitionId;
		/**
		 * 대회에 참여 가능한 팀수
		 */
		private int teamCnt;
		/**
		 * 대회 신청한 팀수
		 */
		private int oteamCnt;
		/**
		 * 체육관ID
		 */
		private Long gymId;
		/**
		 * 체육관 이름
		 */
		private String gymName;
		/**
		 * 개최지
		 */
		private String city;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		/**
		 * 시작 날짜
		 */
		private Date startDate;
		/**
		 * 종료 날짜
		 */
		private Date endDate;
	}

}

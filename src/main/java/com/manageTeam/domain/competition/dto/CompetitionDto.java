package com.manageTeam.domain.competition.dto;

import java.time.LocalDateTime;

import com.manageTeam.global.entity.ActivateStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class CompetitionDto {
	
	/**
	 * @description 대회를 등록 및 수정 DTO
	 * @author skhan
	 */
	@Getter
	@RequiredArgsConstructor
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
		private LocalDateTime startDate;
		/**
		 * 종료 날짜
		 */
		private LocalDateTime endDate;
		
		/**
		 * 테스트용
		 */
		public Save(Long gymId, int teamCnt) {
			this.gymId = gymId;
			this.teamCnt = teamCnt;
		}
	}

}

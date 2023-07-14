package com.manageTeam.domain.competition.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.team.dto.TeamDto;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CompetitionDto {
	
	/**
	 * @description competitionId
	 * @author skhan
	 */
	@Getter
	@Setter
	public static class CompetitionId{
		private Long competitionId;
	}
	
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
		 * 대회이름
		 */
		private String competitionName;
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
		 * 대회이름
		 */
		private String competitionName;
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
	
	@Getter
	@Setter
	public static class DetailInfo{
		/**
		 * 대회ID
		 */
		private Long competitionId;
		/**
		 * 대회이름
		 */
		private String competitionName;
		/**
		 * 대회에 참여 가능한 팀수
		 */
		private int teamCnt;
		/**
		 * 대회 신청한 팀수
		 */
		private int regTeamCount;
		/**
		 * 대회 신청한 팀 리스트
		 */
		private List<TeamDto.List> teamList;
		/**
		 * 체육관ID
		 */
		private Long gymId;
		/**
		 * 체육관 이름
		 */
		private String gymName;
		/**
		 * 상세주소
		 */
		private AddressDto address;
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
		
		public DetailInfo(Competition competition) {
			this.competitionId = competition.getCompetitionId();
			this.competitionName = competition.getCompetitionName();
			this.teamCnt = competition.getTeamCnt();
			this.teamList = competition.getCompetitionTeams().stream()
					.filter(o -> o.getActivateStatus() == ActivateStatus.YES)
					.map(o -> new TeamDto.List(o.getTeam().getTeamId(), o.getTeam().getTeamName()))
					.collect(Collectors.toList());
			this.regTeamCount = teamList.size();
			this.gymId = competition.getGym().getGymId();
			this.gymName = competition.getGym().getGymName();
			this.address = new AddressDto(competition.getGym().getAddress());
			this.activateStatus = competition.getActivateStatus();
			this.startDate = competition.getStartDate();
			this.endDate = competition.getEndDate();
		}
	}

}

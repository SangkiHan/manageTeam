package com.manageTeam.domain.competition.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.team.dto.TeamDto;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class CompetitionDto {
	
	@ApiModel(value = "competitionId DTO")
	@Getter
	@Setter
	public static class CompetitionId{
		@ApiParam(value = "")
		private Long competitionId;
	}
	
	@ApiModel(value = "대회를 등록 및 수정 DTO")
	@Getter
	@NoArgsConstructor
	public static class Save{
		
		@ApiParam(value = "대회ID")
		private Long competitionId;
		
		@ApiParam(value = "대회이름")
		private String competitionName;
		
		@ApiParam(value = "대회 개최지")
		private Long gymId;
		
		@ApiParam(value = "대회에 참여 가능한 팀수")
		private int teamCnt;
		
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
		
		@ApiParam(value = "시작 날짜")
		private Date startDate;
		
		@ApiParam(value = "종료 날짜")
		private Date endDate;
		
	}
	
	@Getter
	@Setter
	public static class Info{
		
		@ApiParam(value = "대회")
		private Long competitionId;
		
		@ApiParam(value = "대회이름")
		private String competitionName;
		
		@ApiParam(value = "대회에 참여 가능한 팀수")
		private int teamCnt;
		
		@ApiParam(value = "대회 신청한 팀수")
		private int oteamCnt;
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "체육관 이름")
		private String gymName;
		
		@ApiParam(value = "개최지")
		private String city;
		
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
		
		@ApiParam(value = "시작 날짜")
		private Date startDate;
		
		@ApiParam(value = "종료 날짜")
		private Date endDate;
	}
	
	@Getter
	@Setter
	public static class DetailInfo{
		
		@ApiParam(value = "대회ID")
		private Long competitionId;
		
		@ApiParam(value = "대회이름")
		private String competitionName;
		
		@ApiParam(value = "대회에 참여 가능한 팀수")
		private int teamCnt;
		
		@ApiParam(value = "대회 신청한 팀수")
		private int regTeamCount;
		
		@ApiParam(value = "대회 신청한 팀 리스트")
		private List<TeamDto.List> teamList;
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "체육관 이름")
		private String gymName;
		
		@ApiParam(value = "상세주소")
		private AddressDto address;
		
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
		
		@ApiParam(value = "시작 날짜")
		private Date startDate;
		
		@ApiParam(value = "종료 날짜")
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
//			this.address = new AddressDto(competition.getGym().getAddress());
			this.activateStatus = competition.getActivateStatus();
			this.startDate = competition.getStartDate();
			this.endDate = competition.getEndDate();
		}
	}

}

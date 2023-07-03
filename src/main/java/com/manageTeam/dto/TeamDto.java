package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Team;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;


public class TeamDto {
	
	@ApiModel(value = "TeamId Dto")
	@Getter
	public static class teamId{
		Long teamId;
	}
	
	@ApiModel(value = "팀 등록 Dto")
	@Getter
	public static class Save{
		private Long teamId;
		private String teamName;
		private String city;
		private ActivateStatus activateStatus;
		
		public Save(String teamName, String city) {
			this.teamName = teamName;
			this.city = city;
		}
	}
	
	@ApiModel(value = "팀 상세 Dto")
	@Getter
	@ToString
	public static class DetailInfo{
		private Long teamId;
		private String teamName;
		private String city;
		
		public DetailInfo(Team team) {
			this.teamId = team.getTeamId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
		}
	}
	
	@ApiModel(value = "팀 목록 Dto")
	@Getter
	@ToString
	public static class Info{
		private Long teamId;
		private String teamName;
		private String city;
		private Long centerCnt;
		private Long pointCnt;
		private Long shootCnt;
		private Long sFowardCnt;
		private Long fFowardCnt;
		private ActivateStatus activateStatus;
		
		public Info(Team team) {
			this.teamId = team.getTeamId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
			this.activateStatus = team.getActivateStatus();
		}
		
		public Info(
				Team team,
				Long centerCnt, 
				Long pointCnt, 
				Long shootCnt, 
				Long sFowardCnt, 
				Long fFowardCnt,
				ActivateStatus activateStatus
				) {
			this.teamId = team.getTeamId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
			this.centerCnt = centerCnt;
			this.pointCnt = pointCnt;
			this.shootCnt = shootCnt;
			this.sFowardCnt = sFowardCnt;
			this.fFowardCnt = fFowardCnt;
			this.activateStatus = activateStatus;
		}
	}
}

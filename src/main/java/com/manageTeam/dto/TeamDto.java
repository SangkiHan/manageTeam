package com.manageTeam.dto;

import com.manageTeam.entity.Team;

import lombok.Getter;
import lombok.ToString;


public class TeamDto {
	
	@Getter
	public static class Id{
		private Long teamId;

		public Id(Long teamId) {
			this.teamId = teamId;
		}
	}
	
	@Getter
	public static class Save{
		private Long teamId;
		private String teamName;
		private String city;
		
		public Save(String teamName, String city) {
			this.teamName = teamName;
			this.city = city;
		}
	}
	
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
		
		public Info(Team team) {
			this.teamId = team.getTeamId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
		}
		
		public Info(
				Team team,
				Long centerCnt, 
				Long pointCnt, 
				Long shootCnt, 
				Long sFowardCnt, 
				Long fFowardCnt
				) {
			this.teamId = team.getTeamId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
			this.centerCnt = centerCnt;
			this.pointCnt = pointCnt;
			this.shootCnt = shootCnt;
			this.sFowardCnt = sFowardCnt;
			this.fFowardCnt = fFowardCnt;
		}
	}
}

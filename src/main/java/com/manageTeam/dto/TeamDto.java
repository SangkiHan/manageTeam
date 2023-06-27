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
		private Long id;
		private String teamName;
		private String city;
		
		public Save(String teamName, String city) {
			this.teamName = teamName;
			this.city = city;
		}
	}
	
	@Getter
	@ToString
	public static class Info{
		private Long id;
		private String teamName;
		private String city;
		
		public Info(Team team) {
			this.id = team.getId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
		}
	}
}

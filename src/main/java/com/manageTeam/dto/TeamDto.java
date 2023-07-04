package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Team;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class TeamDto {
	
	@ApiModel(value = "TeamId Dto")
	@Getter
	@RequiredArgsConstructor
	public static class TeamId{
		/**
		 * 팀 ID
		 */
		private Long teamId;

		public TeamId(Long teamId) {
			this.teamId = teamId;
		}
	}
	
	@ApiModel(value = "상태값 변경 Dto")
	@Getter
	public static class Status{
		/**
		 * 팀 ID
		 */
		private Long teamId;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
	}
	
	@ApiModel(value = "팀 등록 Dto")
	@Getter
	public static class Save{
		/**
		 * 팀 ID
		 */
		private Long teamId;
		/**
		 * 팀명
		 */
		private String teamName;
		/**
		 * 도시
		 */
		private String city;
		/**
		 * 활성화상태
		 */
		private ActivateStatus activateStatus;
		
		public Save() {};
		
		/**
		 * Entity to Dto Constructor
		 */
		public Save(String teamName, String city) {
			this.teamName = teamName;
			this.city = city;
		}
	}
	
	@ApiModel(value = "팀 상세 Dto")
	@Getter
	@ToString
	public static class DetailInfo{
		/**
		 * 팀 ID
		 */
		private Long teamId;
		/**
		 * 팀명
		 */
		private String teamName;
		/**
		 * 도시
		 */
		private String city;
		
		/**
		 * Entity to Dto Constructor
		 */
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
		/**
		 * 팀 ID
		 */
		private Long teamId;
		/**
		 * 팀명
		 */
		private String teamName;
		/**
		 * 도시
		 */
		private String city;
		/**
		 * 센터 명수
		 */
		private Long centerCnt;
		/**
		 * 포인트가드 명수
		 */
		private Long pointCnt;
		/**
		 * 슈팅가드 명수
		 */
		private Long shootCnt;
		/**
		 * 스몰포워드 명수
		 */
		private Long sFowardCnt;
		/**
		 * 파워포워드 명수
		 */
		private Long fFowardCnt;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		
		/**
		 * Entity to Dto Constructor
		 */
		public Info(Team team) {
			this.teamId = team.getTeamId();
			this.teamName = team.getTeamName();
			this.city = team.getCity();
			this.activateStatus = team.getActivateStatus();
		}
		
		/**
		 * Entity to Dto Constructor
		 */
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

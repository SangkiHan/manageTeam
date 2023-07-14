package com.manageTeam.domain.team.dto;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TeamDto {
	
	@ApiModel(value = "TeamId Dto")
	@Getter
	@NoArgsConstructor
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
	@Setter
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
		 * 대표자
		 */
		private String leader;
		/**
		 * 팀등록일
		 */
		private String regDate;
	}
	
	@ApiModel(value = "팀 목록 Dto")
	@Getter
	@Setter
	@NoArgsConstructor
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
		private Long sfowardCnt;
		/**
		 * 파워포워드 명수
		 */
		private Long ffowardCnt;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		
	}
	
	@ApiModel(value = "팀 목록 Dto")
	@Getter
	@Setter
	@NoArgsConstructor
	public static class List{
		/**
		 * 팀 ID
		 */
		private Long teamId;
		/**
		 * 팀명
		 */
		private String teamName;
		
		public List(Long teamId, String teamName) {
			this.teamId = teamId;
			this.teamName = teamName;
		}
	}
}

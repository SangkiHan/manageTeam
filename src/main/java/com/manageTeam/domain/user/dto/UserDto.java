package com.manageTeam.domain.user.dto;

import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserDto {
	
	@Getter
	public static class Save{
		/**
		 * 사용자 ID
		 */
		private String userId;
		/**
		 * 팀 ID
		 */
		private Long team_id;
		/**
		 * 비밀번호
		 */
		private String password;
		/**
		 * 사용자명
		 */
		private String username;
		/**
		 * 주민등록번호
		 */
		private String rsdntRgnmb;
		/**
		 * 전화번호
		 */
		private String phone;
		/**
		 * 주소
		 */
		private AddressDto address;
		/**
		 * 권한
		 */
		private Auth auth;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		/**
		 * Entity to Dto Constructor
		 */
		public Save(String userId, Long team_id, String password, String username, String rsdntRgnmb, String phone,
				AddressDto address, Auth auth, ActivateStatus activateStatus) {
			this.userId = userId;
			this.team_id = team_id;
			this.password = password;
			this.username = username;
			this.rsdntRgnmb = rsdntRgnmb;
			this.phone = phone;
			this.address = address;
			this.auth = auth;
			this.activateStatus = activateStatus;
		}
	}
	
	@Getter
	@Setter
	@ToString
	public static class Info{
		private String userId;
		private Long teamId;
		private String teamName;
		private String password;
		private String username;
		private String rsdntRgnmb;
		private String phone;
		private AddressDto address;
		private Auth auth;
		private ActivateStatus activateStatus;
		
		public Info() {};
	}
}

package com.manageTeam.domain.user.dto;

import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {
	
	@ApiModel(value = "관리인 사용자 화원가입 Dto")
	@Getter
	@NoArgsConstructor
	public static class Save{
		
		@ApiParam(value = "사용자 ID")
		private String userId;
		
		@ApiParam(value = "팀 ID")
		private Long team_id;
		
		@ApiParam(value = "비밀번호")
		private String password;
		
		@ApiParam(value = "사용자명")
		private String username;
		
		@ApiParam(value = "주민등록번호")
		private String rsdntRgnmb;
		
		@ApiParam(value = "전화번호")
		private String phone;
		
		@ApiParam(value = "주소")
		private AddressDto address;
		
		@ApiParam(value = "권한")
		private Auth auth;
		
		@ApiParam(value = "활성화 상태")
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
	
	@ApiModel(value = "사용자정보DTO")
	@Getter
	@Setter
	public static class Info{
		
		@ApiParam(value = "사용자ID")
		private String userId;
		
		@ApiParam(value = "팀ID")
		private Long teamId;
		
		@ApiParam(value = "팀명")
		private String teamName;
		
		@ApiParam(value = "비밀번호")
		private String password;
		
		@ApiParam(value = "사용자명")
		private String username;
		
		@ApiParam(value = "주민등록번호")
		private String rsdntRgnmb;
		
		@ApiParam(value = "휴대전화")
		private String phone;
		
		@ApiParam(value = "주소")
		private AddressDto address;
		
		@ApiParam(value = "권한(GYM:체육관장, TEAM:팀장)")
		private Auth auth;
		
		@ApiParam(value = "활성화상태")
		private ActivateStatus activateStatus;
		
	}
}

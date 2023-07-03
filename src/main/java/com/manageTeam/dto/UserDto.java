package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Auth;

import lombok.Getter;

public class UserDto {
	
	@Getter
	public static class Save{
		private String userId;
		private Long team_id;
		private String password;
		private String username;
		private String rsdntRgnmb;
		private String phone;
		private AddressDto address;
		private Auth auth;
		private ActivateStatus activateStatus;
	}

}

package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Auth;
import com.manageTeam.util.AESUtil;

import lombok.Getter;
import lombok.ToString;

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
		
		public Info(String userId, Long teamId, String teamName, String password, String username, String rsdntRgnmb,
				String phone, AddressDto address, Auth auth, ActivateStatus activateStatus) throws Exception {
			this.userId = userId;
			this.teamId = teamId;
			this.teamName = teamName;
			this.password = AESUtil.decrypt(password);
			this.username = username;
			this.rsdntRgnmb = AESUtil.decrypt(rsdntRgnmb);
			this.phone = AESUtil.decrypt(phone);
			this.address = address;
			this.auth = auth;
			this.activateStatus = activateStatus;
		}
	}
}

package com.manageTeam.dto;

import com.manageTeam.entity.Address;

import lombok.Getter;

public class MemberDto {
	
	@Getter
	public static class Id{
		private Long memberId;
	}
	
	@Getter
	public static class Save{
		private Long memberId;
		private Long teamId;
		private String username;
		private int age;
		private String birth;
		private Address address;
		private String position;
	}
	
	@Getter
	public static class Info{
		private Long memberId;
		private String username;
		private int age;
		private String birth;
		private Address address;
		private String position;
	}

}

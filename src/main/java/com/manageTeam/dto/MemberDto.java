package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Address;
import com.manageTeam.entity.Member;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

public class MemberDto {
	
	@ApiModel(value = "팀원 상태 변경 Dto")
	@Getter
	public static class Status{
		private Long memberId;
		private ActivateStatus activateStatus;
		
		public Status() {};
	}
	
	@ApiModel(value = "팀원 저장 Dto")
	@Getter
	public static class Save{
		private Long memberId;
		private Long teamId;
		private String memberName;
		private int age;
		private String rsdntRgnmb;
		private String phone;
		private AddressDto address;
		private String position;
		
		public Save() {};
		
		public Save(Long teamId, String memberName, int age, String rsdntRgnmb, String phone, AddressDto address,
				String position) {
			this.teamId = teamId;
			this.memberName = memberName;
			this.age = age;
			this.rsdntRgnmb = rsdntRgnmb;
			this.phone = phone;
			this.address = address;
			this.position = position;
		}
	}
	
	@ApiModel(value = "팀원 정보 Dto")
	@Getter
	@ToString
	public static class Info{
		private Long memberId;
		private String memberName;
		private int age;
		private String rsdntRgnmb;
		private String phone;
		private AddressDto address;
		private String teamName;
		private String position;
		
		public Info(Long memberId, 
				String memberName, 
				int age, 
				String rsdntRgnmb, 
				String phone,
				Address address, 
				String position,
				String teamName) {
			this.memberId = memberId;
			this.memberName = memberName;
			this.age = age;
			this.rsdntRgnmb = rsdntRgnmb;
			this.phone = phone;
			this.address = new AddressDto(address);
			this.teamName= teamName;
			this.position = position;
		}
		
		
		public Info(Member member) {
			this.memberId = member.getMemberId();
			this.memberName = member.getMembername();
			this.age = member.getAge();
			this.rsdntRgnmb = member.getRsdntRgnmb();
			this.phone = member.getPhone();
			this.address = new AddressDto(member.getAddress());
			this.position = member.getPosition().toString();
		}
	}
}

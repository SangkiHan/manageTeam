package com.manageTeam.dto;

import com.manageTeam.entity.Address;
import com.manageTeam.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class MemberDto {
	
	@Getter
	public static class Id{
		private Long memberId;

		public Id(Long memberId) {
			this.memberId = memberId;
		}
	}
	
	@Getter
	@RequiredArgsConstructor
	public static class Save{
		private Long memberId;
		private Long teamId;
		private String memberName;
		private int age;
		private String birth;
		private AddressDto address;
		private String position;
		
		public Save(Long teamId, String memberName, int age, String birth, AddressDto address,
				String position) {
			this.teamId = teamId;
			this.memberName = memberName;
			this.age = age;
			this.birth = birth;
			this.address = address;
			this.position = position;
		}
	}
	
	@Getter
	@ToString
	public static class Info{
		private Long memberId;
		private String memberName;
		private int age;
		private String birth;
		private AddressDto address;
		private String teamName;
		private String position;
		
		public Info(Long memberId, 
				String memberName, 
				int age, 
				String birth, 
				Address address, 
				String position,
				String teamName) {
			this.memberId = memberId;
			this.memberName = memberName;
			this.age = age;
			this.birth = birth;
			this.address = new AddressDto(address);
			this.teamName= teamName;
			this.position = position;
		}
		
		
		public Info(Member member) {
			this.memberId = member.getMemberId();
			this.memberName = member.getMembername();
			this.age = member.getAge();
			this.birth = member.getBirth();
			this.address = new AddressDto(member.getAddress());
			this.position = member.getPosition().toString();
		}
	}
}

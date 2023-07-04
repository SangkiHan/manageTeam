package com.manageTeam.dto;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Address;
import com.manageTeam.entity.Member;
import com.manageTeam.util.AESUtil;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

public class MemberDto {
	
	@ApiModel(value = "팀원 상태 변경 Dto")
	@Getter
	public static class Status{
		/**
		 * 팀원ID
		 */
		private Long memberId;
		/**
		 * 활성화 상태
		 */
		private ActivateStatus activateStatus;
		
		public Status() {};
	}
	
	@ApiModel(value = "팀원 저장 Dto")
	@Getter
	public static class Save{
		/**
		 * 팀원ID
		 */
		private Long memberId;
		/**
		 * 팀ID
		 */
		private Long teamId;
		/**
		 * 팀원이름
		 */
		private String memberName;
		/**
		 * 나이
		 */
		private int age;
		/**
		 * 주민번호
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
		 * 포지션
		 */
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
		/**
		 * 팀원ID
		 */
		private Long memberId;
		/**
		 * 팀원이름
		 */
		private String memberName;
		/**
		 * 나이
		 */
		private int age;
		/**
		 * 주민번호
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
		 * 팀명
		 */
		private String teamName;
		/**
		 * 포지션
		 */
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
		
		/**
		 * Entity to Dto Constructor
		 */
		public Info(Member member) {
			this.memberId = member.getMemberId();
			this.memberName = member.getMembername();
			this.age = member.getAge();
			this.rsdntRgnmb = AESUtil.decrypt(member.getRsdntRgnmb());
			this.phone = AESUtil.decrypt(member.getPhone());
			this.address = new AddressDto(member.getAddress());
			this.position = member.getPosition().toString();
		}
	}
}

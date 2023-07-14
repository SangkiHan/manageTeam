package com.manageTeam.domain.member.dto;

import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Position;
import com.manageTeam.global.util.AESUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {
	
	@ApiModel(value = "팀원 상태 변경 Dto")
	@Getter
	@NoArgsConstructor
	public static class Status{
		
		@ApiParam(value = "팀ID")
		private Long memberId;
		
		@ApiParam(value = "활성화 상태")
		private ActivateStatus activateStatus;
		
	}
	
	@ApiModel(value = "팀원 저장 Dto")
	@Getter
	@NoArgsConstructor
	public static class Save{
		
		@ApiParam(value = "팀원ID")
		private Long memberId;
		
		@ApiParam(value = "팀ID")
		private Long teamId;
		
		@ApiParam(value = "팀원이름")
		private String memberName;
		
		@ApiParam(value = "나이")
		private int age;
		
		@ApiParam(value = "주민번호")
		private String rsdntRgnmb;
		
		@ApiParam(value = "전화번호")
		private String phone;
		
		@ApiParam(value = "주소")
		private AddressDto address;
		
		@ApiParam(value = "팀포지션")
		private String position;
		
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
	
	@ApiModel(value = "팀원 목록 정보 Dto")
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Info{
		
		@ApiParam(value = "팀원ID")
		private Long memberId;
		
		@ApiParam(value = "팀원이름")
		private String memberName;
		
		@ApiParam(value = "나이")
		private int age;
		
		@ApiParam(value = "주민번호")
		private String rsdntRgnmb;
		
		@ApiParam(value = "전화번호")
		private String phone;
		
		@ApiParam(value = "주소")
		private AddressDto address;
		
		@ApiParam(value = "팀명")
		private String teamName;
		
		@ApiParam(value = "포지션")
		private Position position;
		
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
			this.position = member.getPosition();
		}

		public Info(Long memberId, String memberName, int age, String rsdntRgnmb, String phone, AddressDto address,
				String teamName, Position position) {
			this.memberId = memberId;
			this.memberName = memberName;
			this.age = age;
			this.rsdntRgnmb = AESUtil.decrypt(rsdntRgnmb);
			this.phone = AESUtil.decrypt(phone);
			this.address = address;
			this.teamName = teamName;
			this.position = position;
		}
	}
}

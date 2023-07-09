package com.manageTeam.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.manageTeam.domain.member.dto.MemberDto;
import com.manageTeam.util.AESUtil;

import lombok.Getter;

/**
 * @description 팀원 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
public class Member extends BaseEntity{
	/**
	 * 팀원ID
	 */
	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long memberId;
	/**
	 * 팀원명
	 */
	@Column(name = "member_name")
	private String membername;
	/**
	 * 나이
	 */
	private int age;
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
	@Embedded
	private Address address;
	/**
	 * 포지션
	 */
	@Enumerated(EnumType.STRING)
	private Position position;
	/**
	 * 활성화 여부
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	/**
	 * 소속팀
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
	public Member() {}
	
	/**
	 * @description 팀원의 활성화 상태를 변경하는 메소드
	 * @param activateStatus Enum.class
	 * @author skhan
	 */
	public void changeStatus(ActivateStatus activateStatus) {
		this.activateStatus = activateStatus;
	}
	
	/**
	 * @description 팀원의 팀을 세팅해주는 메소드
	 * @param Team Entity
	 * @author skhan
	 */
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
		team.addMember();
	}
	
	/**
	 * Dto to Entity Constructor
	 */
	public Member(MemberDto.Save member) {
		this.memberId = member.getMemberId();
		this.membername = member.getMemberName();
		this.age = member.getAge();
		this.rsdntRgnmb = AESUtil.encrypt(member.getRsdntRgnmb());
		this.phone = AESUtil.encrypt(member.getPhone());
		this.address = new Address(member.getAddress());
		this.position = Position.valueOf(member.getPosition());
		this.activateStatus = ActivateStatus.YES;
	}
}

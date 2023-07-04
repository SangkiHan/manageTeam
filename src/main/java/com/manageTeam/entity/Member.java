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

import com.manageTeam.dto.MemberDto;
import com.manageTeam.util.AESUtil;

import lombok.Getter;

/*
 * 팀원 테이블
 * */
@Entity
@Getter
public class Member extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long memberId;
	@Column(name = "member_name")
	private String membername;
	private int age;
	private String rsdntRgnmb;
	private String phone;
	@Embedded
	private Address address;
	@Enumerated(EnumType.STRING)
	private Position position;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
	public Member() {}
	
	public void changeStatus(ActivateStatus activateStatus) {
		this.activateStatus = activateStatus;
	}
	
	public void changeTeam(Team team) {
		this.team = team;
		team.getMembers().add(this);
		team.addMember();
	}
	
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

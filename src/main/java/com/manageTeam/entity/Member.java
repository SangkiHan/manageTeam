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

import lombok.Getter;

@Entity
@Getter
public class Member extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long memberId;
	@Column(name = "member_name")
	private String username;
	private int age;
	private String birth;
	@Embedded
	private Address address;
	private String position;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	
}

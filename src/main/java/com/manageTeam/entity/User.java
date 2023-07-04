package com.manageTeam.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.manageTeam.dto.UserDto;
import com.manageTeam.util.AESUtil;

import lombok.Getter;

/*
 * 사용자 관리 테이블
 * */
@Entity
@Getter
public class User extends BaseEntity{
	
	@Id
	private String userId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	private String password;
	private String username;
	private String rsdntRgnmb;
	private String phone;
	private Address address;
	@Enumerated(EnumType.STRING)
	private Auth auth;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	
	public User() {};
	
	public void setTeam(Team team) {
		this.team = team;
		team.getUsers().add(this);
	}
	
	public User(UserDto.Save user){
		this.userId = user.getUserId();
		this.password =  AESUtil.encrypt(user.getPassword());
		this.username = user.getUsername();
		this.rsdntRgnmb = AESUtil.encrypt(user.getRsdntRgnmb());
		this.phone = AESUtil.encrypt(user.getPhone());
		this.address = new Address(user.getAddress());
		this.auth = user.getAuth();
		this.activateStatus = user.getActivateStatus();
	}
}

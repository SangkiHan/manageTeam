package com.manageTeam.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.manageTeam.domain.user.dto.UserDto;
import com.manageTeam.util.AESUtil;

import lombok.Getter;

/**
 * @description 사용자 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
public class User extends BaseEntity{
	
	/**
	 * 사용자 ID
	 */
	@Id
	private String userId;
	/**
	 * 소속팀
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	/**
	 * 비밀번호
	 */
	private String password;
	/**
	 * 사용자명
	 */
	private String username;
	/**
	 * 주민등록번호
	 */
	private String rsdntRgnmb;
	/**
	 * 핸드폰번호
	 */
	private String phone;
	/**
	 * 주소
	 */
	private Address address;
	/**
	 * 권한
	 */
	@Enumerated(EnumType.STRING)
	private Auth auth;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	
	public User() {};
	
	/**
	 * @description 소속팀을 세팅해준다.
	 * @author skhan
	 */
	public void setTeam(Team team) {
		this.team = team;
		team.getUsers().add(this);
	}
	
	/**
	 * Dto to Entity Constructor
	 */
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

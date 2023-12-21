package com.manageTeam.domain.user.entity;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Address;
import com.manageTeam.global.entity.Auth;
import com.manageTeam.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @description 사용자 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
@NoArgsConstructor
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

	@Builder
	private User(String userId, Team team, String password, String username, String rsdntRgnmb, String phone, Address address, Auth auth, ActivateStatus activateStatus) {
		this.userId = userId;
		this.team = team;
		this.password = password;
		this.username = username;
		this.rsdntRgnmb = rsdntRgnmb;
		this.phone = phone;
		this.address = address;
		this.auth = auth;
		this.activateStatus = activateStatus;
	}

	/**
	 * @description 소속팀을 세팅해준다.
	 * @author skhan
	 */
	public void changeTeam(Team team) {
		this.team = team;
		team.getUsers().add(this);
	}
}

package com.manageTeam.domain.member.entity;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Address;
import com.manageTeam.global.entity.BaseEntity;
import com.manageTeam.global.entity.Position;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @description 팀원 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity{
	/**
	 * 팀원ID
	 */
	@Id @GeneratedValue
	private Long memberId;
	/**
	 * 팀원명
	 */
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
	private Team team;

	@Builder
	private Member(String membername, int age, String rsdntRgnmb, String phone, Address address, Position position, ActivateStatus activateStatus, Team team) {
		this.membername = membername;
		this.age = age;
		this.rsdntRgnmb = rsdntRgnmb;
		this.phone = phone;
		this.address = address;
		this.position = position;
		this.activateStatus = activateStatus;
		this.team = team;
	}

	/**
	 * @description 팀원의 활성화 상태를 변경하는 메소드
	 * @param activateStatus Enum.class
	 * @author skhan
	 */
	public void changeStatus(ActivateStatus activateStatus) {
		this.activateStatus = activateStatus;
	}
}

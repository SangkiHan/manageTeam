package com.manageTeam.domain.gym.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Address;
import com.manageTeam.global.entity.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 체육관 테이블
 * */
@Entity
@Getter
@NoArgsConstructor
public class Gym extends BaseEntity{

	@Id @GeneratedValue
	private Long gymId;
	private String gymName;
	@Embedded
	private Address address;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;

	@Builder
	private Gym(String gymName, Address address, ActivateStatus activateStatus) {
		this.gymName = gymName;
		this.address = address;
		this.activateStatus = activateStatus;
	}
}

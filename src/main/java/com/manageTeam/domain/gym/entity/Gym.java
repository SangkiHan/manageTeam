package com.manageTeam.domain.gym.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.gym.dto.GymDto;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Address;
import com.manageTeam.global.entity.BaseEntity;

import lombok.Getter;

/*
 * 체육관 테이블
 * */
@Entity
@Getter
public class Gym extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "gym_id")
	private Long gymId;
	@Column(name = "gym_name")
	private String gymName;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "gym")
	private List<Reservation> reservations = new ArrayList<>();
	@OneToMany(mappedBy = "gym")
	private List<Competition> competition = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	
	public Gym() {};
	
	public Gym(GymDto.Save gym) {
		this.gymId = gym.getGymId();
		this.gymName = gym.getGymName();
		this.address = new Address(gym.getAddress());
		this.activateStatus = ActivateStatus.YES;
	}
	
	public Gym(GymDto.Info gym) {
		this.gymId = gym.getGymId();
		this.gymName = gym.getGymName();
		this.address = new Address(gym.getAddress());
		this.activateStatus = ActivateStatus.YES;
	}
}

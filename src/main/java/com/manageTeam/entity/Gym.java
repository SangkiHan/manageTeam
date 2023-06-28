package com.manageTeam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.manageTeam.dto.GymDto;

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
	
	public Gym() {};
	
	public Gym(GymDto.Save gym) {
		this.gymId = gym.getGymId();
		this.gymName = gym.getGymName();
		this.address = new Address(gym.getAddress());
	}
	
	public Gym(GymDto.Info gym) {
		this.gymId = gym.getGymId();
		this.gymName = gym.getGymName();
		this.address = new Address(gym.getAddress());
	}
}

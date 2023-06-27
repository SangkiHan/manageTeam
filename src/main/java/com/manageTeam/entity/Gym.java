package com.manageTeam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

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

}

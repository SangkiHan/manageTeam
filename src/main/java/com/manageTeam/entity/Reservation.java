package com.manageTeam.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

/*
 * 체육관 예약건 테이블
 * */
@Entity
@Getter
public class Reservation extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "reservation_id")
	private Long reservationId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id")
	private Gym gym;
	private ActivateStatus activateStatus;

}

package com.manageTeam.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.manageTeam.dto.ReservationDto;

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
	@JoinColumn(name = "gym_id")
	private Gym gym;
	@OneToMany(mappedBy = "reservation")
	private List<ReservationTeam> reservationTeams = new ArrayList<>();
	private int totalTeamCnt;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	
	public Reservation() {}
	
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
		reservationTeams.forEach(team -> team.cancel());
	}
	
	public void createReservation(Gym gym) {
		this.gym = gym;
		gym.getReservations().add(this);
	}

	public Reservation(ReservationDto.Save reservation) {
		this.reservationId = reservation.getReservationId();
		this.startTime = reservation.getStartTime();
		this.endTime = reservation.getEndTime();
		this.totalTeamCnt = reservation.getTotalTeamCnt();
		this.activateStatus = ActivateStatus.YES;
	};
}

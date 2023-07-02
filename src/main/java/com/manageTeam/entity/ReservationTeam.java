package com.manageTeam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class ReservationTeam extends BaseEntity{
	@Id @GeneratedValue
	@Column(name = "reservation_team_id")
	private Long reservationTeamId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	private ActivateStatus activateStatus;
	
	public ReservationTeam() {}
	
	public void setTeam(Team team) {
		team.checkMemberCnt();
		this.team = team;
		team.getReservationTeams().add(this);
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		reservation.getReservationTeams().add(this);
	}
}

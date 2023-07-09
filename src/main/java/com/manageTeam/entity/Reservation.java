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

import com.manageTeam.domain.reservation.dto.ReservationDto;

import lombok.Getter;

/**
 * @description 체육관 예약 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
public class Reservation extends BaseEntity{
	/**
	 * 예약ID
	 */
	@Id @GeneratedValue
	@Column(name = "reservation_id")
	private Long reservationId;
	/**
	 * 예약시작시간
	 */
	private LocalDateTime startTime;
	/**
	 * 예약종료시간
	 */
	private LocalDateTime endTime;
	/**
	 * 체육관ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id")
	private Gym gym;
	/**
	 * 예약한 팀 List
	 */
	@OneToMany(mappedBy = "reservation")
	private List<ReservationTeam> reservationTeams = new ArrayList<>();
	/**
	 * 예약 가능한 팀 수
	 */
	private int totalTeamCnt;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	
	public Reservation() {}
	
	/**
	 * @description 예약건을 취소할 시 예약이 되어있는 팀들의 예약도 취소시킨다.
	 * @author skhan
	 */
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
		reservationTeams.forEach(team -> team.cancel());
	}
	
	/**
	 * @description 예약건에 체육관을 세팅한다.
	 * @author skhan
	 */
	public void createReservation(Gym gym) {
		this.gym = gym;
		gym.getReservations().add(this);
	}

	/**
	 * Dto to Entity Constructor
	 */
	public Reservation(ReservationDto.Save reservation) {
		this.reservationId = reservation.getReservationId();
		this.startTime = reservation.getStartTime();
		this.endTime = reservation.getEndTime();
		this.totalTeamCnt = reservation.getTotalTeamCnt();
		this.activateStatus = ActivateStatus.YES;
	};
}

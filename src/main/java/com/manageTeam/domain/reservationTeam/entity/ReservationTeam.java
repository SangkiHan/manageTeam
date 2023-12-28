package com.manageTeam.domain.reservationTeam.entity;

import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @description 팀 체육관 예약 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
@NoArgsConstructor
public class ReservationTeam extends BaseEntity{
	/**
	 * 예약 팀 ID
	 */
	@Id @GeneratedValue
	private Long reservationTeamId;
	/**
	 * 팀 ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;
	/**
	 * 예약ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Reservation reservation;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;

	@Builder
	private ReservationTeam(Team team, Reservation reservation, ActivateStatus activateStatus) {
		this.team = team;
		this.reservation = reservation;
		this.activateStatus = activateStatus;
	}

	/**
	 * @description 팀예약건의 활성화 상태를 변경한다.
	 * @author skhan
	 * */
	public ReservationTeam(ActivateStatus activateStatus) {
		this.activateStatus = activateStatus;
	}
	
	/**
	 * @description 예약건을 취소한다.
	 * @author skhan
	 * */
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
	}
}

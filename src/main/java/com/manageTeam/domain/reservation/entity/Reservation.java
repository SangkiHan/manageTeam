package com.manageTeam.domain.reservation.entity;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.reservationTeam.entity.ReservationTeam;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.BaseEntity;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 체육관 예약 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
@NoArgsConstructor
public class Reservation extends BaseEntity{
	/**
	 * 예약ID
	 */
	@Id @GeneratedValue
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
	 * 예약 가능한 팀 수
	 */
	private int reservationTeamCnt;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;

	@Builder
	public Reservation( LocalDateTime startTime, LocalDateTime endTime, Gym gym, int totalTeamCnt, int reservationTeamCnt, ActivateStatus activateStatus) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.gym = gym;
		this.totalTeamCnt = totalTeamCnt;
		this.reservationTeamCnt = reservationTeamCnt;
		this.activateStatus = activateStatus;
	}

	/**
	 * @description 예약건을 취소할 시 예약이 되어있는 팀들의 예약도 취소시킨다.
	 * @author skhan
	 */
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
		this.reservationTeamCnt = 0;
		reservationTeams.forEach(ReservationTeam::cancel);
	}

	public void plusReservationTeamCnt(){
		this.reservationTeamCnt++;
	}

	public void checkCnt(){
		if(reservationTeamCnt==totalTeamCnt){
			throw new GlobalException(ErrorCode.RESERVATION_END);
		}
	}
}

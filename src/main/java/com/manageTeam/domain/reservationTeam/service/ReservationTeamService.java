package com.manageTeam.domain.reservationTeam.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.repository.ReservationRepository;
import com.manageTeam.domain.reservationTeam.dto.ReservationTeamDto;
import com.manageTeam.domain.reservationTeam.repository.ReservationTeamRepository;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Reservation;
import com.manageTeam.entity.ReservationTeam;
import com.manageTeam.entity.Team;
import com.manageTeam.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationTeamService {
	
	private final ReservationTeamRepository reservationTeamRepository;
	private final TeamRepository temRepository;
	private final ReservationRepository reservationRepository;
	
	/**
	 * @api /api/reservationTeam/v1/save
	 * @description 특정팀의 체육관을 예약한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	public void save(ReservationTeamDto.Save request) {
		Team team = temRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("RET0001", "해당 팀 데이터가 없음"));
		Reservation reservation = reservationRepository.findById(request.getReservationId())
				.orElseThrow(() -> new GlobalException("RET0002", "해당 예약 데이터가 없음"));
		
		int teamCnt = reservation.getTotalTeamCnt();
		int joinTeamCnt = reservationRepository.findTeamCntReservation(request.getReservationId()).intValue();
		
		if(teamCnt==joinTeamCnt) {
			throw new GlobalException("RET0003", "해당 체육관은 예약이 마감되었습니다.");
		}
		
		checkTime(reservation, team.getTeamId());
		
		ReservationTeam reservationTeam = new ReservationTeam(request.getActivateStatus());
		reservationTeam.setTeam(team);
		reservationTeam.setReservation(reservation);
		
		reservationTeamRepository.save(reservationTeam);
	}
	
	/**
	 * @api /api/reservationTeam/v1/findAllbyTeam
	 * @description 특정팀이 예약한 체육관 목록을 조회한다.
	 * @throws Exception
	 * @author skhan
	 * */
	public Page<ReservationTeamDto.Info> findAllByTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable){
		return reservationTeamRepository.findAllByTeam(teamId, activateStatus, pageable);
	}

	/**
	 * @description 체육관 예약시 해당 시간에 이미 예약된 시간이 있는 지 체크한다.
	 * @throws GlobalException
	 * @author skhan
	 * @param reservation, teamId
	 * */
	public void checkTime(Reservation reservation, Long teamId) {
		ReservationConditionDto.DateCondition condition = new ReservationConditionDto.DateCondition(reservation.getStartTime(), reservation.getEndTime());
		if(!reservationRepository.findReservationByDate(condition,teamId)) {
			throw new GlobalException("RET0004", "해당 시간에 이미 예약된 체육관이 존재합니다.");
		}
	}

}

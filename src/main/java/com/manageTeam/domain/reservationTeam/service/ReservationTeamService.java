package com.manageTeam.domain.reservationTeam.service;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.reservation.service.ReservationReadService;
import com.manageTeam.domain.reservationTeam.dto.ReservationTeamRequest;
import com.manageTeam.domain.reservationTeam.dto.ReservationTeamResponse;
import com.manageTeam.domain.reservationTeam.repository.ReservationTeamRepository;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.service.TeamReadService;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationTeamService {
	
	private final ReservationTeamRepository reservationTeamRepository;
	private final TeamReadService teamReadService;
	private final ReservationReadService reservationReadService;
	
	/**
	 * @api /api/reservationTeam/v1/save
	 * @description 특정팀의 체육관을 예약한다.
	 * @author skhan
	 * */
	public void save(ReservationTeamRequest.Save request) {
		Team team = teamReadService.findById(request.getTeamId());
		Reservation reservation = reservationReadService.findById(request.getReservationId());

		//체육관 예약 팀수와 예약한 팀의 수가 동일 할 시 예약 불가
		reservation.checkCnt();
		
		//해당 팀이 예약 시간에 이미 등록되어 있는 체육관이 있는 지 체크
		checkTime(reservation, team.getTeamId());
		
		reservationTeamRepository.save(request.toEntity(reservation,team));
	}
	
	/**
	 * @api /api/reservationTeam/v1/findAllbyTeam
	 * @description 특정팀이 예약한 체육관 목록을 조회한다.
	 * @author skhan
	 * */
	public Page<ReservationTeamResponse.Info> findAllByTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable){
		return reservationTeamRepository.findAllByTeam(teamId, activateStatus, pageable);
	}

	/**
	 * @description 체육관 예약시 해당 시간에 이미 예약된 시간이 있는 지 체크한다.
	 * @throws GlobalException
	 * @author skhan
	 * @param reservation, teamId
	 * */
	private void checkTime(Reservation reservation, Long teamId) {
		ReservationConditionDto.DateCondition condition = ReservationConditionDto.DateCondition.builder()
			.startDate(reservation.getStartTime())
			.endDate(reservation.getEndTime())
			.build();
		reservationReadService.findReservationByDate(condition, teamId);
	}

}

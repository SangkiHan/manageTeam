package com.manageTeam.domain.reservation.repository;

import com.manageTeam.domain.reservation.dto.ReservationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;

public interface ReservationRepositoryCustom {
	public Page<ReservationResponse.Info> findAllByCondition(ReservationConditionDto.ListCondition conditionDto, Pageable pageable);
	public Long findTeamCntReservation(Long reservationId);
}

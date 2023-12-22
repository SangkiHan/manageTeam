package com.manageTeam.domain.reservationTeam.service;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservationTeam.entity.ReservationTeam;
import com.manageTeam.domain.reservationTeam.repository.ReservationTeamRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationTeamReadService {

    private final ReservationTeamRepository reservationTeamRepository;

    public void findReservationByDate(ReservationConditionDto.DateCondition condition, Long teamId){
        if(!reservationTeamRepository.findReservationByDate(condition,teamId)) {
            throw new GlobalException(ErrorCode.RESERVATION_TIME);
        }
    }
}

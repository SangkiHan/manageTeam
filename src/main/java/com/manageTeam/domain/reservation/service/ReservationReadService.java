package com.manageTeam.domain.reservation.service;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.reservation.repository.ReservationRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationReadService {

    private final ReservationRepository reservationRepository;

    public Reservation findById(Long reservationId){
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new GlobalException(ErrorCode.RESERVATION_UNKNOWN));
    }

    public void findReservationByDate(ReservationConditionDto.DateCondition condition, Long teamId){
        if(!reservationRepository.findReservationByDate(condition,teamId)) {
            throw new GlobalException(ErrorCode.RESERVATION_TIME);
        }
    }


}

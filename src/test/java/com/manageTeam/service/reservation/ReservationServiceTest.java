package com.manageTeam.service.reservation;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.domain.reservation.dto.ReservationRequest;
import com.manageTeam.domain.reservation.repository.ReservationRepository;
import com.manageTeam.domain.reservation.service.ReservationService;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ReservationServiceTest extends CreateEntity {

    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;

    @AfterEach
    void tearDown() {
        reservationRepository.deleteAllInBatch();
        gymRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("체육관 예약을 등록한다.")
    void saveTest(){
        Gym savedGym = gymRepository.save(createGym());
        ReservationRequest.Save request = ReservationRequest.Save.builder()
            .gymId(savedGym.getGymId())
            .totalTeamCnt(10)
            .startTime(LocalDateTime.of(2023,10,31,0,0,0))
            .endTime(LocalDateTime.of(2023,11,1,0,0,0))
            .build();
    }


}

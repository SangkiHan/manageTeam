package com.manageTeam.service.reservation;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.reservation.repository.ReservationRepository;
import com.manageTeam.domain.reservation.service.ReservationReadService;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationReadServiceTest extends CreateEntity {
    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationReadService reservationReadService;

    @AfterEach
    void tearDown() {
        reservationRepository.deleteAllInBatch();
        gymRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("예약을 조회한다.")
    void findByIdTest(){
        Gym savedGym = gymRepository.save(createGym());
        Reservation savedReservation = reservationRepository.save(createReservation(savedGym));

        Reservation findReservation = reservationReadService.findById(savedReservation.getReservationId());

        assertThat(findReservation).extracting("reservationId", "startTime", "endTime", "gym.gymId", "totalTeamCnt", "reservationTeamCnt", "activateStatus")
            .contains(savedReservation.getReservationId(), LocalDateTime.of(2023,10,31,0,0,0), LocalDateTime.of(2023,11,1,0,0,0), savedGym.getGymId(), 10, 0, ActivateStatus.YES);
    }
}

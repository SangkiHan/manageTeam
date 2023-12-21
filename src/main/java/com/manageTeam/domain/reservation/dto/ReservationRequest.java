package com.manageTeam.domain.reservation.dto;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReservationRequest {

    @ApiModel(value = "예약 등록 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{

        @ApiParam(value = "예약ID")
        private Long reservationId;

        @ApiParam(value = "체육관 예약가능한 팀수")
        private int totalTeamCnt;

        @ApiParam(value = "체육관ID")
        private Long gymId;

        @ApiParam(value = "시작날짜")
        private LocalDateTime startTime;

        @ApiParam(value = "종료날짜")
        private LocalDateTime endTime;

        @Builder
        private Save(int totalTeamCnt, Long gymId, LocalDateTime startTime, LocalDateTime endTime) {
            this.totalTeamCnt = totalTeamCnt;
            this.gymId = gymId;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Reservation toEntity(Gym gym){
            return Reservation.builder()
                .totalTeamCnt(totalTeamCnt)
                .gym(gym)
                .startTime(startTime)
                .endTime(endTime)
                .reservationTeamCnt(0)
                .activateStatus(ActivateStatus.YES)
                .build();
        }
    }

    @ApiModel(value = "Id Dto")
    @Getter
    @NoArgsConstructor
    public static class Id{

        @ApiParam(value = "예약ID")
        private Long reservationId;

        public Id(Long reservationId) {
            this.reservationId = reservationId;
        }
    }
}

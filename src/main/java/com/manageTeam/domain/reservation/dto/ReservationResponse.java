package com.manageTeam.domain.reservation.dto;

import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReservationResponse {
    @ApiModel(value = "예약 등록 응답값 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{
        @ApiParam(value = "예약ID")
        private Long reservationId;
        @ApiParam(value = "시작날짜")
        private LocalDateTime startTime;
        @ApiParam(value = "종료날짜")
        private LocalDateTime endTime;
        @ApiParam(value = "체육관ID")
        private Long gymId;
        @ApiParam(value = "체육관 예약가능한 팀수")
        private int totalTeamCnt;
        @ApiParam(value = "체육관 예약한 팀수")
        private int reservationTeamCnt;
        @ApiParam(value = "활성화 여부")
        private ActivateStatus activateStatus;

        @Builder
        private Save(Long reservationId, LocalDateTime startTime, LocalDateTime endTime, Long gymId, int totalTeamCnt, int reservationTeamCnt, ActivateStatus activateStatus) {
            this.reservationId = reservationId;
            this.startTime = startTime;
            this.endTime = endTime;
            this.gymId = gymId;
            this.totalTeamCnt = totalTeamCnt;
            this.reservationTeamCnt = reservationTeamCnt;
            this.activateStatus = activateStatus;
        }

        public static Save of(Reservation reservation){
            return Save.builder()
                .reservationId(reservation.getReservationId())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .gymId(reservation.getGym().getGymId())
                .totalTeamCnt(reservation.getTotalTeamCnt())
                .reservationTeamCnt(reservation.getReservationTeamCnt())
                .activateStatus(reservation.getActivateStatus())
                .build();
        }
    }

    @ApiModel(value = "예약 목록 Dto")
    @Getter
    @NoArgsConstructor
    public static class Info{

        @ApiParam(value = "예약ID")
        private Long reservationId;

        @ApiParam(value = "체육관 예약가능한 팀수")
        private int totalTeamCnt;

        @ApiParam(value = "현재 예약한 팀의 수")
        private Long joinTeam;

        @ApiParam(value = "체육관ID")
        private Long gymId;

        @ApiParam(value = "체육관 이름")
        private String gymName;

        @ApiParam(value = "체육관 주소")
        private AddressDto address;

        @ApiParam(value = "예약 시작시작")
        private LocalDateTime startTime;

        @ApiParam(value = "예약 종료시간")
        private LocalDateTime endTime;
    }
}

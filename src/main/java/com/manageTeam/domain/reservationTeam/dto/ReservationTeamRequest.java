package com.manageTeam.domain.reservationTeam.dto;

import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.reservationTeam.entity.ReservationTeam;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;

public class ReservationTeamRequest {

    @ApiModel(value = "팀 체육관 예약 Dto")
    @Getter
    public static class Save{

        @ApiParam(value = "예약 팀ID")
        private Long reservationTeamId;

        @ApiParam(value = "팀ID")
        private Long teamId;

        @ApiParam(value = "예약ID")
        private Long reservationId;

        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

        @Builder
        private Save(Long teamId, Long reservationId) {
            this.reservationId = reservationId;
            this.activateStatus = ActivateStatus.YES;
        }

        public ReservationTeam toEntity(Reservation reservation, Team team){
            return ReservationTeam.builder()
                .team(team)
                .reservation(reservation)
                .activateStatus(activateStatus)
                .build();
        }


    }
}

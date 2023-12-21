package com.manageTeam.domain.competition.dto;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

public class CompetitionRequest {
    @ApiModel(value = "대회를 등록 및 수정 DTO")
    @Getter
    @NoArgsConstructor
    public static class Save{

        @ApiParam(value = "대회ID")
        private Long competitionId;

        @ApiParam(value = "대회이름")
        private String competitionName;

        @ApiParam(value = "대회 개최지")
        private Long gymId;

        @ApiParam(value = "대회에 참여 가능한 팀수")
        private int teamCnt;

        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

        @ApiParam(value = "시작 날짜")
        private Date startDate;

        @ApiParam(value = "종료 날짜")
        private Date endDate;

        @Builder
        private Save(String competitionName, Long gymId, int teamCnt, ActivateStatus activateStatus, Date startDate, Date endDate) {
            this.competitionName = competitionName;
            this.gymId = gymId;
            this.teamCnt = teamCnt;
            this.activateStatus = activateStatus;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Competition toEntity(Gym gym){
            return Competition.builder()
                .competitionName(competitionName)
                .gym(gym)
                .teamCnt(teamCnt)
                .activateStatus(activateStatus)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        }
    }


    @ApiModel(value = "competitionId DTO")
    @Getter
    @Setter
    public static class CompetitionId{
        @ApiParam(value = "")
        private Long competitionId;
    }
}

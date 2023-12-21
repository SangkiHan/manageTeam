package com.manageTeam.domain.competitionTeam.dto;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

public class CompetitionTeamResponse {

    @ApiModel(value = "대회 참가 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{

        @ApiParam(value = "대회참가ID")
        private Long competitionTeamId;
        @ApiParam(value = "팀ID")
        private Long teamId;
        @ApiParam(value = "대회ID")
        private Long competitionId;
        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

        @Builder
        private Save(Long competitionTeamId, Long teamId, Long competitionId, ActivateStatus activateStatus) {
            this.competitionTeamId = competitionTeamId;
            this.teamId = teamId;
            this.competitionId = competitionId;
            this.activateStatus = activateStatus;
        }

        public static Save of(CompetitionTeam competitionTeam){
            return Save.builder()
                .competitionId(competitionTeam.getCompetitionTeamId())
                .teamId(competitionTeam.getTeam().getTeamId())
                .competitionId(competitionTeam.getCompetition().getCompetitionId())
                .activateStatus(competitionTeam.getActivateStatus())
                .build();
        }
    }
}

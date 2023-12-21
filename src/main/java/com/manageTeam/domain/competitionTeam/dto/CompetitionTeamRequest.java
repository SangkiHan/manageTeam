package com.manageTeam.domain.competitionTeam.dto;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CompetitionTeamRequest {
    @ApiModel(value = "대회 참가 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{
        @ApiParam(value = "팀ID")
        private Long teamId;
        @ApiParam(value = "대회ID")
        private Long competitionId;
        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

        public CompetitionTeam toEntity(Competition competition, Team team){
            return CompetitionTeam.builder()
                .competition(competition)
                .team(team)
                .activateStatus(activateStatus)
                .build();
        }
    }

    @ApiModel(value = "CompetitionTeamId Dto")
    @Getter
    @NoArgsConstructor
    public static class CompetitionTeamId{
        @ApiParam(value = "대회참가ID")
        private Long competitionTeamId;
    }
}

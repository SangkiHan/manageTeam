package com.manageTeam.domain.competition.dto;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionResponse {

    @ApiModel(value = "대회를 등록 및 수정 응답값 DTO")
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

    @Getter
    public static class DetailInfo{

        @ApiParam(value = "대회ID")
        private Long competitionId;

        @ApiParam(value = "대회이름")
        private String competitionName;

        @ApiParam(value = "대회에 참여 가능한 팀수")
        private int teamCnt;

        @ApiParam(value = "대회 신청한 팀수")
        private int regTeamCount;

        @ApiParam(value = "대회 신청한 팀 리스트")
        private List<TeamResponse.List> teamList;

        @ApiParam(value = "체육관ID")
        private Long gymId;

        @ApiParam(value = "체육관 이름")
        private String gymName;

        @ApiParam(value = "상세주소")
        private AddressDto address;

        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

        @ApiParam(value = "시작 날짜")
        private Date startDate;

        @ApiParam(value = "종료 날짜")
        private Date endDate;

        @Builder
        private DetailInfo(Long competitionId, String competitionName, int teamCnt, int regTeamCount, List<TeamResponse.List> teamList, Long gymId, String gymName, AddressDto address, ActivateStatus activateStatus, Date startDate, Date endDate) {
            this.competitionId = competitionId;
            this.competitionName = competitionName;
            this.teamCnt = teamCnt;
            this.regTeamCount = regTeamCount;
            this.teamList = teamList;
            this.gymId = gymId;
            this.gymName = gymName;
            this.address = address;
            this.activateStatus = activateStatus;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public static DetailInfo of(Competition competition){
            return DetailInfo.builder()
                .competitionId(competition.getCompetitionId())
                .competitionName(competition.getCompetitionName())
                .teamCnt(competition.getTeamCnt())
                .regTeamCount(competition.getRegTeamCnt())
                .teamList(competition.getCompetitionTeams().stream()
                    .map(team -> TeamResponse.List.of(team.getTeam()))
                    .collect(Collectors.toList())
                )
                .gymId(competition.getGym().getGymId())
                .gymName(competition.getGym().getGymName())
                .address(AddressDto.of(competition.getGym().getAddress()))
                .activateStatus(competition.getActivateStatus())
                .startDate(competition.getStartDate())
                .endDate(competition.getEndDate())
                .build();
        }
    }

    @Getter
    public static class Info{

        @ApiParam(value = "대회")
        private Long competitionId;

        @ApiParam(value = "대회이름")
        private String competitionName;

        @ApiParam(value = "대회에 참여 가능한 팀수")
        private int teamCnt;

        @ApiParam(value = "대회 신청한 팀수")
        private int oteamCnt;

        @ApiParam(value = "체육관ID")
        private Long gymId;

        @ApiParam(value = "체육관 이름")
        private String gymName;

        @ApiParam(value = "개최지")
        private String city;

        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

        @ApiParam(value = "시작 날짜")
        private Date startDate;

        @ApiParam(value = "종료 날짜")
        private Date endDate;
    }
}

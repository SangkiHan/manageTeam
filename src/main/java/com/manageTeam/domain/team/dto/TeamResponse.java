package com.manageTeam.domain.team.dto;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class TeamResponse {
    @ApiModel(value = "팀 등록 응답값")
    @Getter
    public static class Save{
        /**
         * 팀 ID
         */
        private Long teamId;
        /**
         * 팀명
         */
        private String teamName;
        /**
         * 도시
         */
        private String city;
        /**
         * 활성화상태
         */
        private ActivateStatus activateStatus;

        @Builder
        private Save(Long teamId, String teamName, String city, ActivateStatus activateStatus) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.city = city;
            this.activateStatus = activateStatus;
        }

        public Team toEntity(){
            return Team.builder()
                .city(city)
                .teamName(teamName)
                .memberCount(0)
                .activateStatus(activateStatus)
                .build();
        }

        public static TeamResponse.Save of(Team team){
            return Save.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .city(team.getCity())
                .activateStatus(team.getActivateStatus())
                .build();
        }
    }

    @ApiModel(value = "팀 상세 Dto")
    @Getter
    @Setter
    public static class DetailInfo{
        /**
         * 팀 ID
         */
        private Long teamId;
        /**
         * 팀명
         */
        private String teamName;
        /**
         * 도시
         */
        private String city;
        /**
         * 대표자
         */
        private String leader;
        /**
         * 팀등록일
         */
        private LocalDateTime regDate;

        @Builder
        private DetailInfo(Long teamId, String teamName, String city, String leader, LocalDateTime regDate) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.city = city;
            this.leader = leader;
            this.regDate = regDate;
        }

        public static DetailInfo of(Team team){
            return DetailInfo.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .city(team.getCity())
                .leader(team.getUser()==null ? "담당자 없음" : team.getUser().getUsername())
                .regDate(team.getCreatedDate())
                .build();
        }
    }

    @ApiModel(value = "팀 목록 Dto")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Info{
        /**
         * 팀 ID
         */
        private Long teamId;
        /**
         * 팀명
         */
        private String teamName;
        /**
         * 도시
         */
        private String city;
        /**
         * 센터 명수
         */
        private Long centerCnt;
        /**
         * 포인트가드 명수
         */
        private Long pointCnt;
        /**
         * 슈팅가드 명수
         */
        private Long shootCnt;
        /**
         * 스몰포워드 명수
         */
        private Long sfowardCnt;
        /**
         * 파워포워드 명수
         */
        private Long ffowardCnt;
        /**
         * 활성화 상태
         */
        private ActivateStatus activateStatus;

    }

    @ApiModel(value = "팀 목록 Dto")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class List{
        /**
         * 팀 ID
         */
        private Long teamId;
        /**
         * 팀명
         */
        private String teamName;

        @Builder
        private List(Long teamId, String teamName) {
            this.teamId = teamId;
            this.teamName = teamName;
        }

        public static List of(Team team){
            return List.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .build();
        }
    }
}

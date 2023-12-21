package com.manageTeam.domain.team.dto;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamRequest {
    @ApiModel(value = "팀 등록 Dto")
    @Getter
    public static class Save{
        /**
         * 팀명
         */
        private String teamName;
        /**
         * 도시
         */
        private String city;
        /**
         * 활성화 상태
         */
        private ActivateStatus activateStatus;

        @Builder
        private Save(Long teamId, String teamName, String city, ActivateStatus activateStatus) {
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
    }

    @ApiModel(value = "상태값 변경 Dto")
    @Getter
    public static class Status{
        /**
         * 팀 ID
         */
        private Long teamId;
        /**
         * 활성화 상태
         */
        private ActivateStatus activateStatus;
    }
}

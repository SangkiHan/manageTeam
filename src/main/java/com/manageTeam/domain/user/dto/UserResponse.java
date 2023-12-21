package com.manageTeam.domain.user.dto;

import com.manageTeam.domain.user.entity.User;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserResponse {
    @ApiModel(value = "사용자정보DTO")
    @Getter
    @Setter
    public static class Info{

        @ApiParam(value = "사용자ID")
        private String userId;

        @ApiParam(value = "팀ID")
        private Long teamId;

        @ApiParam(value = "팀명")
        private String teamName;

        @ApiParam(value = "사용자명")
        private String username;

        @ApiParam(value = "주민등록번호")
        private String rsdntRgnmb;

        @ApiParam(value = "휴대전화")
        private String phone;

        @ApiParam(value = "주소")
        private AddressDto address;

        @ApiParam(value = "권한(GYM:체육관장, TEAM:팀장)")
        private Auth auth;

        @ApiParam(value = "활성화상태")
        private ActivateStatus activateStatus;

        @Builder
        private Info(String userId, Long teamId, String teamName, String username, String rsdntRgnmb, String phone, AddressDto address, Auth auth, ActivateStatus activateStatus) {
            this.userId = userId;
            this.teamId = teamId;
            this.teamName = teamName;
            this.username = username;
            this.rsdntRgnmb = rsdntRgnmb;
            this.phone = phone;
            this.address = address;
            this.auth = auth;
            this.activateStatus = activateStatus;
        }

        public static Info of(User user){
            return Info.builder()
                .userId(user.getUserId())
                .teamId(user.getTeam().getTeamId())
                .teamName(user.getTeam().getTeamName())
                .rsdntRgnmb(user.getRsdntRgnmb())
                .phone(user.getPhone())
                .auth(user.getAuth())
                .activateStatus(user.getActivateStatus())
                .build();
        }
    }
}

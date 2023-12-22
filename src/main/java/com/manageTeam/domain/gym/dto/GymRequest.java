package com.manageTeam.domain.gym.dto;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GymRequest {
    @ApiModel(value = "체육관 등록,수정 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{

        @ApiParam(value = "체육관 명")
        private String gymName;

        @ApiParam(value = "체육관 주소")
        private AddressDto address;

        @Builder
        private Save(String gymName, AddressDto address) {
            this.gymName = gymName;
            this.address = address;
        }

        public Gym toEntity(){
            return Gym.builder()
                .gymName(gymName)
                .activateStatus(ActivateStatus.YES)
                .address(address.toEntity())
                .build();
        }
    }
}

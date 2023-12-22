package com.manageTeam.domain.gym.dto;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.global.dto.AddressDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GymResponse {
    @ApiModel(value = "체육관 정보 Dto")
    @Getter
    @NoArgsConstructor
    public static class Info{

        @ApiParam(value = "체육관ID")
        private Long gymId;

        @ApiParam(value = "체육관 명")
        private String gymName;

        @ApiParam(value = "체육관 주소")
        private AddressDto address;

        @Builder
        private Info(Long gymId, String gymName, AddressDto address) {
            this.gymId = gymId;
            this.gymName = gymName;
            this.address = address;
        }

        public static Info of(Gym gym){
            return Info.builder()
                .gymId(gym.getGymId())
                .gymName(gym.getGymName())
                .address(AddressDto.of(gym.getAddress()))
                .build();
        }
    }

    @ApiModel(value = "체육관 등록,수정 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{

        @ApiParam(value = "체육관 ID")
        private Long gymId;

        @ApiParam(value = "체육관 명")
        private String gymName;

        @ApiParam(value = "체육관 주소")
        private AddressDto address;

        @Builder
        private Save(Long gymId, String gymName, AddressDto address) {
            this.gymId = gymId;
            this.gymName = gymName;
            this.address = address;
        }

        public static Save of(Gym gym){
            return Save.builder()
                .gymId(gym.getGymId())
                .gymName(gym.getGymName())
                .address(AddressDto.of(gym.getAddress()))
                .build();
        }
    }
}

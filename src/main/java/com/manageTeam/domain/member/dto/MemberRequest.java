package com.manageTeam.domain.member.dto;

import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequest {
    @ApiModel(value = "팀원 저장 Dto")
    @Getter
    @NoArgsConstructor
    public static class Save{

        @ApiParam(value = "팀원ID")
        private Long memberId;

        @ApiParam(value = "팀ID")
        private Long teamId;

        @ApiParam(value = "팀원이름")
        private String memberName;

        @ApiParam(value = "나이")
        private int age;

        @ApiParam(value = "주민번호")
        private String rsdntRgnmb;

        @ApiParam(value = "전화번호")
        private String phone;

        @ApiParam(value = "주소")
        private AddressDto address;

        @ApiParam(value = "팀포지션")
        private Position position;

        @Builder
        private Save(Long teamId, String memberName, int age, String rsdntRgnmb, String phone, AddressDto address,
                     Position position) {
            this.teamId = teamId;
            this.memberName = memberName;
            this.age = age;
            this.rsdntRgnmb = rsdntRgnmb;
            this.phone = phone;
            this.address = address;
            this.position = position;
        }

        public Member toEntity(Team team){
            return Member.builder()
                .team(team)
                .membername(memberName)
                .activateStatus(ActivateStatus.YES)
                .address(address.toEntity())
                .phone(phone)
                .rsdntRgnmb(rsdntRgnmb)
                .age(age)
                .position(position)
                .build();
        }
    }

    @ApiModel(value = "팀원 상태 변경 Dto")
    @Getter
    @NoArgsConstructor
    public static class Status{

        @ApiParam(value = "팀ID")
        private Long memberId;

        @ApiParam(value = "활성화 상태")
        private ActivateStatus activateStatus;

    }
}

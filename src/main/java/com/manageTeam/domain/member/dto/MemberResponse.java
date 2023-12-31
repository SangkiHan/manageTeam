package com.manageTeam.domain.member.dto;

import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberResponse {
    @ApiModel(value = "팀원 목록 정보 Dto")
    @Getter
    @Setter
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

        @ApiParam(value = "포지션")
        private Position position;

        @Builder
        public Save(Long memberId, Long teamId, String memberName, int age, String rsdntRgnmb, String phone, AddressDto address, Position position) {
            this.memberId = memberId;
            this.teamId = teamId;
            this.memberName = memberName;
            this.age = age;
            this.rsdntRgnmb = rsdntRgnmb;
            this.phone = phone;
            this.address = address;
            this.position = position;
        }

        public static Save of(Member member){
            return Save.builder()
                .memberId(member.getMemberId())
                .teamId(member.getTeam().getTeamId())
                .memberName(member.getMembername())
                .age(member.getAge())
                .rsdntRgnmb(member.getRsdntRgnmb())
                .phone(member.getPhone())
                .address(AddressDto.of(member.getAddress()))
                .position(member.getPosition())
                .build();
        }
    }
    @ApiModel(value = "팀원 목록 정보 Dto")
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Info{

        @ApiParam(value = "팀원ID")
        private Long memberId;

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

        @ApiParam(value = "팀명")
        private String teamName;

        @ApiParam(value = "포지션")
        private Position position;

        @ApiParam(value = "활동여부")
        private ActivateStatus activateStatus;

        @Builder
        public Info(Long memberId, String memberName, int age, String rsdntRgnmb, String phone, AddressDto address, String teamName, Position position, ActivateStatus activateStatus) {
            this.memberId = memberId;
            this.memberName = memberName;
            this.age = age;
            this.rsdntRgnmb = rsdntRgnmb;
            this.phone = phone;
            this.address = address;
            this.teamName = teamName;
            this.position = position;
            this.activateStatus = activateStatus;
        }

        public static Info of(Member member){
            return Info.builder()
                .memberId(member.getMemberId())
                .memberName(member.getMembername())
                .age(member.getAge())
                .rsdntRgnmb(member.getRsdntRgnmb())
                .phone(member.getPhone())
                .address(AddressDto.of(member.getAddress()))
                .teamName(member.getTeam().getTeamName())
                .position(member.getPosition())
                .activateStatus(member.getActivateStatus())
                .build();
        }
    }
}

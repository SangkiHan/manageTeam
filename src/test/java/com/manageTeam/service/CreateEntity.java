package com.manageTeam.service;

import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;
import com.manageTeam.global.entity.Position;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateEntity {
    public Team createTeam(){
        return Team.builder()
            .teamName("창공")
            .city("SUWON")
            .activateStatus(ActivateStatus.YES)
            .memberCount(0)
            .build();
    }

    public User createUser(Team team){
        return User.builder()
            .userId("tkdrl8908")
            .team(team)
            .password("1234")
            .username("한상기")
            .rsdntRgnmb("1234561234567")
            .phone("01012341234")
            .address(createAddress().toEntity())
            .auth(Auth.TEAM)
            .activateStatus(ActivateStatus.YES)
            .build();
    }

    public AddressDto createAddress(){
        return AddressDto.builder()
            .city("SUWON")
            .street("harylero")
            .zipcode("12345")
            .build();
    }

    public Member createMember(Team team){
        return Member.builder()
            .team(team)
            .membername("상기")
            .age(29)
            .rsdntRgnmb("123412341234")
            .address(createAddress().toEntity())
            .phone("01012341234")
            .position(Position.C)
            .activateStatus(ActivateStatus.YES)
            .build();
    }
}

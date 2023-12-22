package com.manageTeam.service;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;
import com.manageTeam.global.entity.Position;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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

    public Gym createGym(){
        return Gym.builder()
            .gymName("보훈원")
            .address(createAddress().toEntity())
            .activateStatus(ActivateStatus.YES)
            .build();
    }

    public Reservation createReservation(Gym gym){
        return Reservation.builder()
            .gym(gym)
            .totalTeamCnt(10)
            .reservationTeamCnt(0)
            .startTime(LocalDateTime.of(2023, 10, 31, 0, 0, 0))
            .endTime(LocalDateTime.of(2023, 11, 1, 0, 0, 0))
            .activateStatus(ActivateStatus.YES)
            .build();
    }
}

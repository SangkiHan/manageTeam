package com.manageTeam.service;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.user.dto.UserRequest;
import com.manageTeam.domain.user.dto.UserResponse;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.domain.user.service.UserService;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamRepository teamRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("사용자를 저장한다.")
    void saveTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        UserRequest.Save request = createUserSaveDto(savedTeam.getTeamId());
        UserResponse.Info info = userService.save(request);

        assertThat(info).extracting("userId", "teamId", "teamName", "username", "rsdntRgnmb", "phone", "auth", "activateStatus")
            .contains("tkdrl8908", savedTeam.getTeamId(), "창공", "한상기", "1234561234567", "01012341234", Auth.TEAM, ActivateStatus.YES);
    }

    private Team createTeam(){
        return Team.builder()
            .teamName("창공")
            .city("SUWON")
            .activateStatus(ActivateStatus.YES)
            .memberCount(0)
            .build();
    }

    private UserRequest.Save createUserSaveDto(Long teamId){
        return UserRequest.Save.builder()
            .userId("tkdrl8908")
            .username("한상기")
            .password("1234")
            .auth(Auth.TEAM)
            .phone("01012341234")
            .rsdntRgnmb("1234561234567")
            .team_id(teamId)
            .address(createAddress())
            .build();
    }

    private AddressDto createAddress(){
        return AddressDto.builder()
            .city("SUWON")
            .street("harylero")
            .zipcode("12345")
            .build();
    }

}

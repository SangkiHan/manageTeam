package com.manageTeam.service.user;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.user.dto.UserResponse;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.domain.user.service.UserReadService;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Auth;
import com.manageTeam.global.exception.GlobalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class UserReadServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserReadService userReadService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("사용자를 조회한다.")
    @Transactional
    void findUserInfoTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        User user = userRepository.save(createUser(savedTeam));

        UserResponse.Info info = userReadService.findUserInfo(user.getUserId());

        assertThat(info).extracting("userId", "teamId", "teamName", "username", "rsdntRgnmb", "phone", "auth", "activateStatus")
            .contains("tkdrl8908", savedTeam.getTeamId(), "창공", "한상기", "1234561234567", "01012341234", Auth.TEAM, ActivateStatus.YES);
    }

    @Test
    @DisplayName("User Entity를 조회한다.")
    void findByUserIdTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        User user = userRepository.save(createUser(savedTeam));

        User findUser = userReadService.findByUserId(user.getUserId());

        assertThat(findUser).extracting("userId", "password", "username", "rsdntRgnmb", "phone", "auth", "activateStatus")
            .contains("tkdrl8908", "1234", "한상기", "1234561234567", "01012341234", Auth.TEAM, ActivateStatus.YES);
    }

    @Test
    @DisplayName("주민번호로 이미 존재하는 사용자인지 체크한다.")
    void existsByRsdntRgnmbTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        userRepository.save(createUser(savedTeam));

        assertThatThrownBy(() -> userReadService.existsByRsdntRgnmb("1234561234567"))
            .isInstanceOf(GlobalException.class);
    }

    private Team createTeam(){
        return Team.builder()
            .teamName("창공")
            .city("SUWON")
            .activateStatus(ActivateStatus.YES)
            .memberCount(0)
            .build();
    }

    private User createUser(Team team){
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

    private AddressDto createAddress(){
        return AddressDto.builder()
            .city("SUWON")
            .street("harylero")
            .zipcode("12345")
            .build();
    }
}

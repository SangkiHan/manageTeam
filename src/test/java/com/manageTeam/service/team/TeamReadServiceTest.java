package com.manageTeam.service.team;

import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.team.service.TeamReadService;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TeamReadServiceTest extends CreateEntity {

    @Autowired
    private TeamReadService teamReadService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("팀을 조회한다.")
    void findByIdTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        Team findTeam = teamReadService.findById(savedTeam.getTeamId());

        assertThat(findTeam).extracting("teamId", "teamName", "city", "memberCount", "activateStatus")
            .contains(findTeam.getTeamId(), "창공", "SUWON", 0, ActivateStatus.YES);
    }

    @Test
    @DisplayName("해당 지역에 동일한 이름인 팀이 존재하면 예외가 발생한다.")
    void existsByTeamNameAndCityTest(){
        Team team = createTeam();
        teamRepository.save(team);

        assertThatThrownBy(() -> teamReadService.existsByTeamNameAndCity("창공", "SUWON"))
            .isInstanceOf(GlobalException.class);
    }

    @Test
    @DisplayName("저장되어있는 팀의 상세정보를 조회한다.")
    void findOneTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        TeamResponse.DetailInfo info = teamReadService.findOne(savedTeam.getTeamId());

        assertThat(info).extracting("teamId", "teamName", "city", "leader")
            .contains(savedTeam.getTeamId(), "창공", "SUWON", "담당자 없음");
    }

    @Test
    @DisplayName("저장되어있는 팀의 상세정보를 조회한다.")
    void findOneWithUserTest(){
        Team team = createTeam();
        Team savedTeam = teamRepository.save(team);

        User user = createUser(team);
        userRepository.save(user);

        TeamResponse.DetailInfo info = teamReadService.findOne(savedTeam.getTeamId());

        assertThat(info).extracting("teamId", "teamName", "city", "leader")
            .contains(savedTeam.getTeamId(), "창공", "SUWON", "한상기");
    }
}

package com.manageTeam.service.team;

import com.manageTeam.domain.team.dto.TeamRequest;
import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.team.service.TeamReadService;
import com.manageTeam.domain.team.service.TeamService;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamServiceTest extends CreateEntity {
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamReadService teamReadService;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("팀을 등록한다.")
    void saveTest(){
        TeamRequest.Save request = TeamRequest.Save.builder()
            .teamName("창공")
            .city("SUWON")
            .activateStatus(ActivateStatus.YES)
            .build();

        TeamResponse.Save response = teamService.save(request);

        assertThat(response).extracting("teamId", "teamName", "city", "activateStatus")
            .contains(response.getTeamId(), "창공", "SUWON", ActivateStatus.YES);
    }

    @Test
    @DisplayName("팀의 활동 상태를 변경한다.")
    void changeStatusTest(){
        Team savedTeam = teamRepository.save(createTeam());

        TeamRequest.Status status = TeamRequest.Status.builder()
            .teamId(savedTeam.getTeamId())
            .activateStatus(ActivateStatus.NO)
            .build();

        teamService.status(status);

        Team findTeam = teamReadService.findById(savedTeam.getTeamId());

        assertThat(findTeam.getActivateStatus()).isEqualTo(ActivateStatus.NO);
    }

}

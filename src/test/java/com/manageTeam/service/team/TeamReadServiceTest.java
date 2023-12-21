package com.manageTeam.service.team;

import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.team.service.TeamReadService;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.exception.GlobalException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class TeamReadServiceTest {

    @Autowired
    private TeamReadService teamReadService;
    @Autowired
    private TeamRepository teamRepository;

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

    private Team createTeam(){
        return Team.builder()
            .teamName("창공")
            .city("SUWON")
            .activateStatus(ActivateStatus.YES)
            .memberCount(0)
            .build();
    }
}

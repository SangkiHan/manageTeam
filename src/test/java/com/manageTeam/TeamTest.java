package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.manageTeam.dto.TeamDto;
import com.manageTeam.service.TeamService;

@SpringBootTest
public class TeamTest {
	
	@Autowired TeamService teamService;
	
	@Test	
	@Rollback(value = true)
	void save() {
		TeamDto.Save teamDto = new TeamDto.Save("창공","수원시");
		teamService.save(teamDto);
	}
	
	@Test	
	void findById() {
		TeamDto.Id teamId = new TeamDto.Id(1L);
		TeamDto.Info team = teamService.findById(teamId);
		System.out.println(team);
	}

}

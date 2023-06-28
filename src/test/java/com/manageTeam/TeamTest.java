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
	
	/*
	 * 저장 테스트
	 * */
	@Test	
	@Rollback(value = true)
	void save() {
		TeamDto.Save teamDto = new TeamDto.Save("창공","수원시");
		teamService.save(teamDto);
	}
	
	/*
	 * 단건 조회 테스트
	 * */
	@Test	
	void findById() {
		TeamDto.Info team = teamService.findById(1L);
		System.out.println(team);
	}

}

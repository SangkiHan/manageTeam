package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competition.service.CompetitionService;

@SpringBootTest
public class CompetitionTest {
	
	@Autowired private CompetitionService competitionService;
	
	@Test
	void save() {
		CompetitionDto.Save info = new CompetitionDto.Save(1L, 8);
		competitionService.save(info);
	}

}

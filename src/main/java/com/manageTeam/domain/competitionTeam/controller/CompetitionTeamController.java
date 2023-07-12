package com.manageTeam.domain.competitionTeam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamDto;
import com.manageTeam.domain.competitionTeam.service.CompetitionTeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competitionTeam")
public class CompetitionTeamController {
	
	private final CompetitionTeamService competitionTeamService;
	
	/**
	 * @description 대회를 등록 및 수정한다.
	 * @author skhan
	 */
	@PostMapping("/v1/save")
	public void save(@RequestBody CompetitionTeamDto.Save request) {
		competitionTeamService.save(request);
	}

}

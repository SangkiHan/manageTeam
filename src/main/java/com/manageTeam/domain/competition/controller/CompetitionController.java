package com.manageTeam.domain.competition.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competition.service.CompetitionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/competition")
@RequiredArgsConstructor
public class CompetitionController {
	
	private final CompetitionService competitionService;
	
	/**
	 * @description 대회를 등록 및 수정한다.
	 * @author skhan
	 */
	@PostMapping(value = "/v1/save")
	public void save(@RequestBody CompetitionDto.Save request) {
		competitionService.save(request);
	}

}

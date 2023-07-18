package com.manageTeam.domain.competitionTeam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamDto;
import com.manageTeam.domain.competitionTeam.service.CompetitionTeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api("대회 참가 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competitionTeam")
public class CompetitionTeamController {
	
	private final CompetitionTeamService competitionTeamService;
	
	@ApiOperation(value="대회 참가 신청을 한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody CompetitionTeamDto.Save request) {
		competitionTeamService.save(request);
	}
	
	@ApiOperation(value="대회 참가 신청을 취소한다.")
	@PostMapping("/v1/cancel")
	public void cancel(@RequestBody CompetitionTeamDto.CompetitionTeamId request) {
		competitionTeamService.cancel(request);
	}

}

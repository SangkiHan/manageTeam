package com.manageTeam.domain.team.controller;

import com.manageTeam.domain.team.dto.TeamRequest;
import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.service.TeamReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.service.TeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api("팀 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
	
	private final TeamService teamService;
	private final TeamReadService teamReadService;
	
	@ApiOperation(value="팀 저장 및 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody TeamRequest.Save request) {
		teamService.save(request);
	}
	
	@ApiOperation(value="팀의 활성화 상태를 번경한다.")
	@PostMapping("/v1/status")
	public void status(@RequestBody TeamRequest.Status request) {
		teamService.status(request);
	}
	
	@ApiOperation(value="팀상세를 조회한다.")
	@GetMapping("/v1/{teamId}")
	public TeamResponse.DetailInfo findOne(@PathVariable(name = "teamId") Long teamId) {
		return teamReadService.findOne(teamId);
	}
	
	@ApiOperation(value="팀목록을 조회한다.")
	@GetMapping("/v1/findAll")
	public Page<TeamResponse.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable) {
		return teamReadService.findAllByCondition(conditionDto, pageable);
	}
}

package com.manageTeam.domain.team.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamDto;
import com.manageTeam.domain.team.service.TeamService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
	
	private final TeamService teamService;
	
	@ApiOperation(value="팀 저장 및 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody TeamDto.Save request) {
		teamService.save(request);
	}
	
	@ApiOperation(value="팀의 활성화 상태를 번경한다.")
	@PostMapping("/v1/status")
	public void status(@RequestBody TeamDto.Status request) {
		teamService.status(request);
	}
	
	@ApiOperation(value="팀상세를 조회한다.")
	@PostMapping("/v1/findOne")
	public TeamDto.DetailInfo findOne(@RequestBody TeamDto.TeamId request) {
		return teamService.findOne(request);
	}
	
	@ApiOperation(value="팀목록을 조회한다.")
	@GetMapping("/v1/findAll")
	public Page<TeamDto.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable) {
		return teamService.findAllByCondition(conditionDto, pageable);
	}
}

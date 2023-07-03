package com.manageTeam.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.TeamConditionDto;
import com.manageTeam.dto.TeamDto;
import com.manageTeam.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
	
	private final TeamService teamService;
	
	/*
	 * 팀저장, 수정
	 * */
	@PostMapping("/v1/save")
	public void save(@RequestBody TeamDto.Save request) {
		teamService.save(request);
	}
	
	/*
	 * 팀 활성화 상태 변경
	 * */
	@PostMapping("/v1/status")
	public void status(@RequestBody TeamDto.Status request) {
		teamService.activate(request);
	}
	
	/*
	 * 팀상세
	 * */
	@GetMapping("/v1/findById")
	public TeamDto.Info findById(@RequestBody TeamDto.TeamId request) {
		return teamService.findById(request);
	}
	
	/*
	 * 팀목록
	 * */
	@GetMapping("/v1/findAll")
	public Page<TeamDto.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable) {
		return teamService.findAllByCondition(conditionDto, pageable);
	}
}

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
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
public class TeamController {
	
	private final TeamService teamService;
	
	/**
	 * @description 팀 저장 및 수정한다.
	 * @throws Exception
	 * @author skhan
	 */
	@PostMapping("/v1/save")
	public void save(@RequestBody TeamDto.Save request) {
		teamService.save(request);
	}
	
	/**
	 * @description 팀의 활성화 상태를 번경한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 */
	@PostMapping("/v1/status")
	public void status(@RequestBody TeamDto.Status request) {
		teamService.status(request);
	}
	
	/**
	 * @description 팀상세를 조회한다.
	 * @throws Exception
	 * @author skhan
	 */
	@PostMapping("/v1/findById")
	public TeamDto.Info findById(@RequestBody TeamDto.TeamId request) {
		return teamService.findById(request);
	}
	
	/**
	 * @description 팀목록을 조회한다.
	 * @throws Exception
	 * @author skhan
	 */
	@GetMapping("/v1/findAll")
	public Page<TeamDto.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable) {
		return teamService.findAllByCondition(conditionDto, pageable);
	}
}

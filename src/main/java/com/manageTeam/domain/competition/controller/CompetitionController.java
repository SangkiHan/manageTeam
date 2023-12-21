package com.manageTeam.domain.competition.controller;

import com.manageTeam.domain.competition.dto.CompetitionRequest;
import com.manageTeam.domain.competition.dto.CompetitionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.service.CompetitionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api("대회API")
@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {
	
	private final CompetitionService competitionService;
	
	@ApiOperation(value="대회를 등록 및 수정한다.")
	@PostMapping(value = "/v1/save")
	public void save(@RequestBody CompetitionRequest.Save request) {
		competitionService.save(request);
	}
	
	@ApiOperation(value="대회를 상세 조회 한다.")
	@GetMapping("/v1/find{competitionId}")
	public CompetitionResponse.DetailInfo findOne(@PathVariable(name = "competitionId") Long competitionId){
		return competitionService.findOne(competitionId);
	}
	
	@ApiOperation(value="현재 등록되어 있는 대회목록을 조회한다.")
	@GetMapping(value = "/v1/findAll")
	public Page<CompetitionResponse.Info> findAllByContidtion(CompetitionConditionDto condition, Pageable pageable){
		return competitionService.findAllByCondition(condition, pageable);
	}
	
	@ApiOperation(value="대회 개최를 취소한다.")
	@PostMapping(value = "/v1/cancel")
	public void cancel(@RequestBody CompetitionRequest.CompetitionId request) {
		competitionService.cancel(request);
	}

}

package com.manageTeam.domain.competition.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competition.service.CompetitionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/competition")
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
	
	/**
	 * @description 대회를 상세 조회 한다.
	 * @author skhan
	 */
	@GetMapping("/v1/findOne")
	public CompetitionDto.DetailInfo findOne(Long competitionId){
		return competitionService.findOne(competitionId);
	}
	
	
	/**
	 * @description 현재 등록되어 있는 대회목록을 조회한다.
	 * @author skhan
	 */
	@GetMapping(value = "/v1/findAll")
	public Page<CompetitionDto.Info> findAllByContidtion(CompetitionConditionDto condition, Pageable pageable){
		return competitionService.findAllByCondition(condition, pageable);
	}
	
	/**
	 * @description 대회 개최를 취소한다.
	 * @author skhan
	 */
	@PostMapping(value = "/v1/cancel")
	public void cancel(@RequestBody CompetitionDto.CompetitionId request) {
		competitionService.cancel(request);
	}

}

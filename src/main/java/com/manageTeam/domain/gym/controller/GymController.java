package com.manageTeam.domain.gym.controller;

import com.manageTeam.domain.gym.dto.GymRequest;
import com.manageTeam.domain.gym.dto.GymResponse;
import com.manageTeam.domain.gym.service.GymReadService;
import org.springframework.web.bind.annotation.*;

import com.manageTeam.domain.gym.service.GymService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api("체육관 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gym")
public class GymController {
	
	private final GymService gymService;
	private final GymReadService gymReadService;
	
	@ApiOperation(value="체육관을 저장 및 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody GymRequest.Save request) {
		gymService.save(request);
	}
	
	@ApiOperation(value="체육관을 상세조회한다.")
	@GetMapping("/v1/find/{gymId}")
	public GymResponse.Info findById(@PathVariable(name = "gymId") Long gymId){
		return gymReadService.findOne(gymId);
	}
	
}

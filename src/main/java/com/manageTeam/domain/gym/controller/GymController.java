package com.manageTeam.domain.gym.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.gym.dto.GymDto;
import com.manageTeam.domain.gym.service.GymService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gym")
public class GymController {
	
	private final GymService gymService;
	
	@ApiOperation(value="체육관을 저장 및 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody GymDto.Save request) {
		gymService.save(request);
	}
	
	@ApiOperation(value="체육관을 상세조회한다.")
	@GetMapping("/v1/findById")
	public GymDto.Info findById(Long gymId){
		return gymService.findById(gymId);
	}
	
}

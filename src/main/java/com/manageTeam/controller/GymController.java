package com.manageTeam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.GymDto;
import com.manageTeam.service.GymService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gym")
public class GymController {
	
	private final GymService gymService;
	
	/**
	 * 체육관 저장 및 수정
	 * 
	 * @author skhan
	 */
	@PostMapping("/v1/save")
	public void save(@RequestBody GymDto.Save request) {
		gymService.save(request);
	}
	
	/**
	 * 체육관 상세조회
	 * 
	 * @author skhan
	 */
	@GetMapping("/v1/findById")
	public GymDto.Info findById(Long gymId){
		return gymService.findById(gymId);
	}
	
}

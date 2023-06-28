package com.manageTeam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.ReservationDto;
import com.manageTeam.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
	
	private final ReservationService reservationService;
	
	/*
	 * 체육관 예약 등록
	 * */
	@PostMapping("/v1/save")
	public void save(@RequestBody ReservationDto.Save request) {
		reservationService.save(request);
	}
}

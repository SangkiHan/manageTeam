package com.manageTeam.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.ReservationConditionDto;
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
	
	/*
	 * 체육관 예약 삭제
	 * */
	@PostMapping("/v1/cancel")
	public void cancel(@RequestBody ReservationDto.Id request) {
		reservationService.cancel(request);
	}
	
	/*
	 * 등록된 예약목록
	 * */
	@GetMapping("/v1/findAll")
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto.ListCondition conditionDto, Pageable pageable) {
		return reservationService.findAllByCondition(conditionDto, pageable);
	}
}

package com.manageTeam.domain.reservation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.dto.ReservationDto;
import com.manageTeam.domain.reservation.service.ReservationService;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
	
	private final ReservationService reservationService;
	
	/**
	 * @description 체육관 예약을 등록한다.
	 * @throws Exception
	 * @author skhan
	 * */
	@PostMapping("/v1/save")
	public void save(@RequestBody ReservationDto.Save request) {
		reservationService.save(request);
	}
	
	/**
	 * @description 체육관 예약등록을 취소한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	@PostMapping("/v1/cancel")
	public void cancel(@RequestBody ReservationDto.Id request) {
		reservationService.cancel(request);
	}
	
	/**
	 * @description 등록된 체육관 예약목록을 조회한다.
	 * @throws Exception
	 * @author skhan
	 * */
	@GetMapping("/v1/findAll")
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto.ListCondition conditionDto, Pageable pageable) {
		return reservationService.findAllByCondition(conditionDto, pageable);
	}
}

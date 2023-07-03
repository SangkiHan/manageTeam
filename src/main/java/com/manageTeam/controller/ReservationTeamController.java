package com.manageTeam.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.service.ReservationTeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationTeam")
public class ReservationTeamController {
	
	private final ReservationTeamService reservationTeamService;
	
	/*
	 * 특정팀이 예약한 체육관 목록 보기
	 * */
	@GetMapping("/findAllbyTeam")
	public Page<ReservationTeamDto.Info> findAllbyTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable){
		return reservationTeamService.findAllByTeam(teamId, activateStatus, pageable);
	}
	
	/*
	 * 특정팀 쳬육관예약
	 * */
	@PostMapping("/save")
	public void save(@RequestBody ReservationTeamDto.Save request){
		reservationTeamService.save(request);
	}

}

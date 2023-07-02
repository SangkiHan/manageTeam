package com.manageTeam.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.service.ReservationTeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationTeam")
public class ReservationTeamController {
	
	private final ReservationTeamService reservationTeamService;
	
	@GetMapping("/findAllbyTeam")
	public Page<ReservationTeamDto.Info> findAllbyTeam(Long teamId, Pageable pageable){
		return reservationTeamService.findAllByTeam(teamId, pageable);
	}

}

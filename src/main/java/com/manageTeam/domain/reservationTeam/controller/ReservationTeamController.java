package com.manageTeam.domain.reservationTeam.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.reservationTeam.dto.ReservationTeamDto;
import com.manageTeam.domain.reservationTeam.service.ReservationTeamService;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationTeam")
public class ReservationTeamController {
	
	private final ReservationTeamService reservationTeamService;
	
	/**
	 * @description 특정팀의 체육관을 예약한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	@PostMapping("/v1/save")
	public void save(@RequestBody ReservationTeamDto.Save request){
		reservationTeamService.save(request);
	}
	
	/**
	 * @description 특정팀이 예약한 체육관 목록을 조회한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	@GetMapping("/v1/findAllbyTeam")
	public Page<ReservationTeamDto.Info> findAllbyTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable){
		return reservationTeamService.findAllByTeam(teamId, activateStatus, pageable);
	}
}

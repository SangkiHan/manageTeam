package com.manageTeam.domain.reservationTeam.controller;

import com.manageTeam.domain.reservationTeam.dto.ReservationTeamRequest;
import com.manageTeam.domain.reservationTeam.dto.ReservationTeamResponse;
import com.manageTeam.domain.reservationTeam.service.ReservationTeamService;
import com.manageTeam.global.entity.ActivateStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api("체육관 예약 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationTeam")
public class ReservationTeamController {
	
	private final ReservationTeamService reservationTeamService;
	
	@ApiOperation(value="특정팀의 체육관을 예약한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody ReservationTeamRequest.Save request){
		reservationTeamService.save(request);
	}
	
	@ApiOperation(value="특정팀이 예약한 체육관 목록을 조회한다.")
	@GetMapping("/v1/findAllbyTeam")
	public Page<ReservationTeamResponse.Info> findAllbyTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable){
		return reservationTeamService.findAllByTeam(teamId, activateStatus, pageable);
	}
}

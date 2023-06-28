package com.manageTeam.service;

import org.springframework.stereotype.Service;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.entity.Reservation;
import com.manageTeam.entity.ReservationTeam;
import com.manageTeam.entity.Team;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.ReservationRepository;
import com.manageTeam.repository.ReservationTeamRepository;
import com.manageTeam.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationTeamService {
	
	private final ReservationTeamRepository reservationTeamRepository;
	private final TeamRepository temRepository;
	private final ReservationRepository reservationRepository;
	
	public void save(ReservationTeamDto.Save request) {
		Team team = temRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("RET0001","해당 팀 데이터가 없음"));
		Reservation reservation = reservationRepository.findById(request.getReservationId())
				.orElseThrow(() -> new GlobalException("RET0002","해당 예약 데이터가 없음"));
		
		ReservationTeam reservationTeam = new ReservationTeam();
		reservationTeam.setTeam(team);
		reservationTeam.setReservation(reservation);
		
		reservationTeamRepository.save(reservationTeam);
	}
	
	public void checkTime(Reservation reservation) {
		
	}

}

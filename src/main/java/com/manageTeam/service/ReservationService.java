package com.manageTeam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.dto.ReservationConditionDto;
import com.manageTeam.dto.ReservationDto;
import com.manageTeam.entity.Gym;
import com.manageTeam.entity.Reservation;
import com.manageTeam.entity.ReservationTeam;
import com.manageTeam.entity.User;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.ReservationRepository;
import com.manageTeam.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final UserRepository userRepository;
	private final GymService gymService;
	
	public void save(ReservationDto.Save request) {
		Gym gym = new Gym(gymService.findById(request.getGymId()));
		Reservation reservation = new Reservation(request);
		reservation.createReservation(gym);
		reservationRepository.save(reservation);
	}
	
	public void cancel(ReservationDto.Id request) {
		Reservation reservation = reservationRepository.findById(request.getReservationId())
				.orElseThrow(() -> new GlobalException("RES0001","해당 예약이 존재하지 않습니다. 관리자에게 문의해주세요."));
		
		reservation.cancel();
		
		List<ReservationTeam> teams = reservation.getReservationTeams();
		if(teams.size()>0) {
			for(ReservationTeam team : teams) {
				User user = userRepository.findByTeam(team.getTeam())
						.orElseThrow(() -> new GlobalException("RES0002","해당 팀의 관리자가 존재하지 않습니다. 관리자에게 문의해주세요."));
				
				String phone = user.getPhone();
				System.out.println(phone+"에 문자발송");
			}
		}
	}
	
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto.ListCondition reservationConditionDto, Pageable pageable) {
		return reservationRepository.findAllByCondition(reservationConditionDto, pageable);
	}

}

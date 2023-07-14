package com.manageTeam.domain.reservation.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.service.GymService;
import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.dto.ReservationDto;
import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.domain.reservation.repository.ReservationRepository;
import com.manageTeam.domain.reservationTeam.entity.ReservationTeam;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final UserRepository userRepository;
	private final GymService gymService;
	
	/**
	 * @api /api/reservation/v1/save
	 * @description 체육관 예약을 등록한다.
	 * @author skhan
	 * */
	public void save(ReservationDto.Save request) {
		Gym gym = new Gym(gymService.findById(request.getGymId()));
		Reservation reservation = new Reservation(request);
		reservation.createReservation(gym);
		reservationRepository.save(reservation);
	}
	
	/**
	 * @api /api/reservation/v1/cancel
	 * @description 체육관 예약등록을 취소한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public void cancel(ReservationDto.Id request) {
		Reservation reservation = reservationRepository.findById(request.getReservationId())
				.orElseThrow(() -> new GlobalException(ErrorCode.RESERVATION_UNKNOWN));
		
		reservation.cancel();
		
		List<ReservationTeam> teams = reservation.getReservationTeams();
		//체육관이 취소되었을경우 각 팀 대표자들에게 문자 전송
		if(teams.size()>0) {
			for(ReservationTeam team : teams) {
				User user = userRepository.findByTeam(team.getTeam())
						.orElseThrow(() -> new GlobalException(ErrorCode.TEAM_MANAGER));
				
				String phone = user.getPhone();
				System.out.println(phone+"에 문자발송");
			}
		}
	}
	
	/**
	 * @api /api/reservation/v1/findAll
	 * @description 등록된 체육관 예약목록을 조회한다.
	 * @author skhan
	 * */
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto.ListCondition reservationConditionDto, Pageable pageable) {
		return reservationRepository.findAllByCondition(reservationConditionDto, pageable);
	}

}

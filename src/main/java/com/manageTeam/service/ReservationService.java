package com.manageTeam.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.dto.ReservationConditionDto;
import com.manageTeam.dto.ReservationDto;
import com.manageTeam.entity.Gym;
import com.manageTeam.entity.Reservation;
import com.manageTeam.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final GymService gymService;
	
	public void save(ReservationDto.Save request) {
		Gym gym = new Gym(gymService.findById(request.getGymId()));
		Reservation reservation = new Reservation(request);
		reservation.createReservation(gym);
		reservationRepository.save(reservation);
	}
	
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto.ListCondition reservationConditionDto, Pageable pageable) {
		return reservationRepository.findAllByCondition(reservationConditionDto, pageable);
	}

}

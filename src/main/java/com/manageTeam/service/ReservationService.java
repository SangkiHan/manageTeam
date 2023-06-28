package com.manageTeam.service;

import org.springframework.stereotype.Service;

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
}

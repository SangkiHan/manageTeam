package com.manageTeam.domain.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manageTeam.domain.reservation.entity.Reservation;
import com.manageTeam.global.entity.ActivateStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom{
	Optional<Reservation> findByReservationIdAndActivateStatus(Long id, ActivateStatus activateStatus);
}

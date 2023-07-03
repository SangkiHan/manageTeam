package com.manageTeam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryCustom{
	@Query("select r from Reservation r join fetch r.reservationTeams rt join fetch rt.team where r.reservationId = :id")
	Optional<Reservation> findById(@Param("id") Long id);
	Optional<Reservation> findByReservationIdAndActivateStatus(Long id, ActivateStatus activateStatus);
}

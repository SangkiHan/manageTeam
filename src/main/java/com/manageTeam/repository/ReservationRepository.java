package com.manageTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

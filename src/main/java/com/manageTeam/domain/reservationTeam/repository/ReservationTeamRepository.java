package com.manageTeam.domain.reservationTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.global.entity.ReservationTeam;

public interface ReservationTeamRepository extends JpaRepository<ReservationTeam, Long>, ReservationTeamRepositoryCustom{

}

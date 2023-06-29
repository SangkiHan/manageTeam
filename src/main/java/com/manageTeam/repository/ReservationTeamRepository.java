package com.manageTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.entity.ReservationTeam;

public interface ReservationTeamRepository extends JpaRepository<ReservationTeam, Long>, ReservationTeamRepositoryCustom{

}

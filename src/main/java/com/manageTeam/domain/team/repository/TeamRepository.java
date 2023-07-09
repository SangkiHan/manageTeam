package com.manageTeam.domain.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.global.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom{
	
}

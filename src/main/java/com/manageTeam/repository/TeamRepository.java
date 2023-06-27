package com.manageTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom{
	
}

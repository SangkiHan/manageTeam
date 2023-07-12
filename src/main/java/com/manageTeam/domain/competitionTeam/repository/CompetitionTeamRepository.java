package com.manageTeam.domain.competitionTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;

public interface CompetitionTeamRepository extends JpaRepository<CompetitionTeam, Long>, CompetitionTeamRepositoryCustom{

}

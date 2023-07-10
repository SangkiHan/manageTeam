package com.manageTeam.domain.competition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.domain.competition.entity.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long>, CompetitionRepositoryCustom{

}

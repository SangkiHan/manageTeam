package com.manageTeam.domain.competition.repository;

import java.util.Optional;

import com.manageTeam.domain.competition.dto.CompetitionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.entity.Competition;

public interface CompetitionRepositoryCustom {
	public boolean checkCompetitionGym(CompetitionConditionDto.DateCheck request);
	public Page<CompetitionResponse.Info> findAllByCondition(CompetitionConditionDto request, Pageable pageable);
	public Optional<Competition> findOne(Long competitionId);
}

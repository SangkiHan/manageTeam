package com.manageTeam.domain.competition.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.dto.CompetitionDto;

public interface CompetitionRepositoryCustom {
	public boolean checkCompetitionGym(CompetitionConditionDto.DateCheck request);
	public Page<CompetitionDto.Info> findAllByCondition(CompetitionConditionDto request, Pageable pageable);
}

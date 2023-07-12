package com.manageTeam.domain.competition.repository;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;

public interface CompetitionRepositoryCustom {
	public boolean checkCompetitionGym(CompetitionConditionDto.DateCheck request);
}

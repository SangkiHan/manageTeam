package com.manageTeam.domain.competitionTeam.repository;

import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamContidtionDto;

public interface CompetitionTeamRepositoryCustom {
	public boolean checkCompetitionTeamDate(CompetitionTeamContidtionDto.checkDate request);
}

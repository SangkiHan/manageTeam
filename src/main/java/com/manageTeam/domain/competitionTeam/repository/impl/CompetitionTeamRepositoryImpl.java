package com.manageTeam.domain.competitionTeam.repository.impl;

import static com.manageTeam.domain.competitionTeam.entity.QCompetitionTeam.competitionTeam;
import static com.manageTeam.domain.team.entity.QTeam.team;

import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamContidtionDto;
import com.manageTeam.domain.competitionTeam.repository.CompetitionTeamRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompetitionTeamRepositoryImpl implements CompetitionTeamRepositoryCustom{
	
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public boolean checkCompetitionTeamDate(CompetitionTeamContidtionDto.checkDate request) {
		return jpaQueryFactory
				.select(competitionTeam.count())
				.from(competitionTeam)
				.join(competitionTeam.team, team)
				.on(competitionTeam.activateStatus.eq(ActivateStatus.YES))
				.where(
						competitionTeam.team.teamId.eq(request.getTeamId()),
						competitionTeam.competition.startDate.goe(request.getEndDate()),
						competitionTeam.competition.endDate.loe(request.getStartDate())
						)
				.fetchOne()<1;
	}
}
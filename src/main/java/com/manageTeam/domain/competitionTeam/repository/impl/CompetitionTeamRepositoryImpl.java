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

	/**
	 * @description 해당 날짜에 이미 등록되어있는 대회여부를 조회한다.
	 * @author skhan
	 */
	@Override
	public boolean checkCompetitionTeamDate(CompetitionTeamContidtionDto.checkDate request) {
		Long count = jpaQueryFactory
				.select(competitionTeam.count())
				.from(competitionTeam)
				.join(competitionTeam.team, team)
				.on(competitionTeam.activateStatus.eq(ActivateStatus.YES))
				.where(
						competitionTeam.team.teamId.eq(request.getTeamId()),
						competitionTeam.competition.startDate.loe(request.getEndDate()),
						competitionTeam.competition.endDate.goe(request.getStartDate())
						)
				.fetchOne();

		return count != null && count < 0;
	}
}

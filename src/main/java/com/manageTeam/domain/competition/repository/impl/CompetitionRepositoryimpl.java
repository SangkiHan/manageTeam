package com.manageTeam.domain.competition.repository.impl;

import static com.manageTeam.domain.competition.entity.QCompetition.competition;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.repository.CompetitionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompetitionRepositoryImpl implements CompetitionRepositoryCustom{
	
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public boolean checkCompetitionGym(CompetitionConditionDto.DateCheck request) {
		return jpaQueryFactory
				.select(competition.count())
				.from(competition)
				.where(
						competition.startDate.goe(request.getEndDate()),
						competition.endDate.loe(request.getStartDate()),
						competition.gym.gymId.eq(request.getGymId())
						)
				.fetchOne()<0;
	}

}

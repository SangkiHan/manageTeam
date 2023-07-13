package com.manageTeam.domain.competition.repository.impl;

import static com.manageTeam.domain.competition.entity.QCompetition.competition;
import static com.manageTeam.domain.competitionTeam.entity.QCompetitionTeam.competitionTeam;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competition.repository.CompetitionRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompetitionRepositoryimpl implements CompetitionRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public boolean checkCompetitionGym(CompetitionConditionDto.DateCheck request) {
		return queryFactory
				.select(competition.count())
				.from(competition)
				.where(
						startDateGeo(request.getEndDate()),
						endDateLoe(request.getStartDate()),
						competition.gym.gymId.eq(request.getGymId())
						)
				.fetchOne()<1;
	}

	@Override
	public Page<CompetitionDto.Info> findAllByCondition(CompetitionConditionDto request, Pageable pageable) {
		Predicate predicate = new BooleanBuilder()
						.and(startDateGeo(request.getEndDate()))
						.and(endDateLoe(request.getStartDate()))
						.and(cityEq(request.getCity()))
						.and(activateStatusEq(request.getActivateStatus()));
		
		List<CompetitionDto.Info> results = queryFactory
				.select(Projections.bean(CompetitionDto.Info.class, 
						competition.competitionId,
						competition.teamCnt,
						competitionTeam.count().intValue().as("oteamCnt"),
						competition.gym.gymName,
						competition.gym.gymId,
						competition.gym.address.city,
						competition.activateStatus,
						competition.startDate,
						competition.endDate
						))
				.from(competition)
				.leftJoin(competition.CompetitionTeams, competitionTeam)
				.on(competitionTeam.activateStatus.eq(ActivateStatus.YES))
				.where(predicate)
				.groupBy(competition.competitionId)
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(competition.count())
				.from(competition)
				.where(predicate);
		
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
	
	
	
	public BooleanExpression startDateGeo(Date startDate) {
		return startDate!=null ? competition.startDate.goe(startDate) : null;
	}
	public BooleanExpression endDateLoe(Date endDate) {
		return endDate!=null ? competition.endDate.loe(endDate) : null;
	}
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? competition.gym.address.city.eq(city) : null;
	}
	public BooleanExpression activateStatusEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? competition.activateStatus.eq(activateStatus) : null;
	}

	@Override
	public Competition findCompetition(Long competitionId) {
		return queryFactory
				.select(competition)
				.from(competition)
				.join(competition.CompetitionTeams, competitionTeam)
				.fetchJoin()
				.fetchOne();
	}
}

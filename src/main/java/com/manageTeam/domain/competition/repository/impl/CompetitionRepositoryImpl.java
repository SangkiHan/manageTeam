package com.manageTeam.domain.competition.repository.impl;

import static com.manageTeam.domain.competition.entity.QCompetition.competition;
import static com.manageTeam.domain.competitionTeam.entity.QCompetitionTeam.competitionTeam;
import static com.manageTeam.domain.gym.entity.QGym.gym;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.manageTeam.domain.competition.dto.CompetitionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
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
public class CompetitionRepositoryImpl implements CompetitionRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	/**
	 * @description 해당 날짜에 이미 등록된 대회가 있는 지 조회
	 * @author skhan
	 */
	@Override
	public boolean checkCompetitionGym(CompetitionConditionDto.DateCheck request) {
		Long count = queryFactory
				.select(competition.count())
				.from(competition)
				.where(
						startDateGeo(request.getEndDate()),
						endDateLoe(request.getStartDate()),
						competition.gym.gymId.eq(request.getGymId())
						)
				.fetchOne();

		return count != null && count < 0;
	}

	/**
	 * @description 등록된 대회 목록 조회
	 * @author skhan
	 * @where endDate, startDate, city, activateStatus
	 */
	@Override
	public Page<CompetitionResponse.Info> findAllByCondition(CompetitionConditionDto request, Pageable pageable) {
		Predicate predicate = new BooleanBuilder()
						.and(startDateGeo(request.getEndDate()))
						.and(endDateLoe(request.getStartDate()))
						.and(cityEq(request.getCity()))
						.and(activateStatusEq(request.getActivateStatus()));
		
		List<CompetitionResponse.Info> results = queryFactory
				.select(Projections.bean(CompetitionResponse.Info.class,
						competition.competitionId,
						competition.competitionName,
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
				.leftJoin(competition.competitionTeams, competitionTeam)
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
	
	/**
	 * @description startDate >= request
	 * @author skhan
	 */
	public BooleanExpression startDateGeo(Date startDate) {
		return startDate!=null ? competition.startDate.goe(startDate) : null;
	}
	/**
	 * @description endDate <= request
	 * @author skhan
	 */
	public BooleanExpression endDateLoe(Date endDate) {
		return endDate!=null ? competition.endDate.loe(endDate) : null;
	}
	/**
	 * @description city = request
	 * @author skhan
	 */
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? competition.gym.address.city.eq(city) : null;
	}
	/**
	 * @description activateStatus = request
	 * @author skhan
	 */
	public BooleanExpression activateStatusEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? competition.activateStatus.eq(activateStatus) : null;
	}
	
	/**
	 * @description competition entity 조회
	 * @author skhan
	 */
	@Override
	public Optional<Competition> findOne(Long competitionId) {
		return Optional.ofNullable(queryFactory
				.select(competition)
				.from(competition)
				.join(competition.gym, gym).fetchJoin()
				.fetchOne());
	}
}

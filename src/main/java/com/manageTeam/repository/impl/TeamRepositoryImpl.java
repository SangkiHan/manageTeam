package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QMember.member;
import static com.manageTeam.entity.QTeam.team;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.dto.TeamConditionDto;
import com.manageTeam.dto.TeamDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Position;
import com.manageTeam.repository.TeamRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public TeamDto.Info findTeamInfo(Long teamId) {
		
		TeamDto.Info results = queryFactory
				.select(Projections.constructor(TeamDto.Info.class,
						team,
						new CaseBuilder().when(member.position.eq(Position.C)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.PG)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.SG)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.SF)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.PF)).then(1).otherwise(Expressions.nullExpression()).count(),
						team.activateStatus
						))
				.from(team)
				.leftJoin(team.members, member)
				.where(
						team.teamId.eq(teamId)
						)
				.fetchOne();
		
		return results;
	}

	@Override
	public Page<TeamDto.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable) {
		
		List<TeamDto.Info> results = queryFactory
				.select(Projections.constructor(TeamDto.Info.class,
						team,
						new CaseBuilder().when(member.position.eq(Position.C)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.PG)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.SG)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.SF)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.PF)).then(1).otherwise(Expressions.nullExpression()).count(),
						team.activateStatus
						))
				.from(team)
				.join(team.members, member)
				.where(
						teamnameEq(conditionDto.getTeamName()),
						activatestatusEq(conditionDto.getActivateStatus()),
						cityEq(conditionDto.getCity())
						)
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(team.count())
				.from(team);
		
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
	
	public BooleanExpression teamnameEq(String membername) {
		return StringUtils.hasText(membername) ? team.teamName.eq(membername) : null;
	}
	public BooleanExpression activatestatusEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? team.activateStatus.eq(activateStatus) : null;
	}
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? team.city.eq(city) : null;
	}
}

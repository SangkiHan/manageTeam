package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QMember.member;
import static com.manageTeam.entity.QTeam.team;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.manageTeam.dto.MemberConditionDto;
import com.manageTeam.dto.TeamDto;
import com.manageTeam.entity.Position;
import com.manageTeam.repository.TeamRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<TeamDto.Info> findAllByCondition(MemberConditionDto conditionDto, Pageable pageable) {
		
		List<TeamDto.Info> results = queryFactory
				.select(Projections.constructor(TeamDto.Info.class,
						team,
						new CaseBuilder().when(member.position.eq(Position.C)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.PG)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.SG)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.SF)).then(1).otherwise(Expressions.nullExpression()).count(),
						new CaseBuilder().when(member.position.eq(Position.PF)).then(1).otherwise(Expressions.nullExpression()).count()
						))
				.from(team)
				.join(team.members, member)
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(team.count())
				.from(team);
		
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
}

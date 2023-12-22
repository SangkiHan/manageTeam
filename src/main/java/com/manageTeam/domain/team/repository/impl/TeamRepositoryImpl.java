package com.manageTeam.domain.team.repository.impl;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.repository.TeamRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Position;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.manageTeam.domain.member.entity.QMember.member;
import static com.manageTeam.domain.team.entity.QTeam.team;
import static com.manageTeam.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	/**
	 * @description 팀목록을 조회한다.
	 * @author skhan
	 */
	@Override
	public Page<TeamResponse.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable) {
		Predicate predicate = new BooleanBuilder()
				.and(teamnameEq(conditionDto.getTeamName()))
				.and(activatestatusEq(conditionDto.getActivateStatus()))
				.and(cityEq(conditionDto.getCity()));
		
		List<TeamResponse.Info> results = queryFactory
				.select(Projections.bean(TeamResponse.Info.class,
						team.teamId,
						team.teamName,
						team.city,
						new CaseBuilder()
							.when(
								member.position.eq(Position.C)).then(1)
							.otherwise(Expressions.nullExpression()).count().as("centerCnt"),
						new CaseBuilder()
							.when(member.position.eq(Position.PG)).then(1)
							.otherwise(Expressions.nullExpression()).count().as("pointCnt"),
						new CaseBuilder()
							.when(member.position.eq(Position.SG)).then(1)
							.otherwise(Expressions.nullExpression()).count().as("shootCnt"),
						new CaseBuilder()
							.when(member.position.eq(Position.SF)).then(1)
							.otherwise(Expressions.nullExpression()).count().as("sfowardCnt"),
						new CaseBuilder()
							.when(member.position.eq(Position.PF)).then(1)
							.otherwise(Expressions.nullExpression()).count().as("ffowardCnt"),
						team.activateStatus
						))
				.from(team)
				.leftJoin(team.members, member)
				.where(predicate)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.groupBy(team.teamId)
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(team.count())
				.from(team)
				.where(predicate);
		
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
	/**
	 * @description memberName = request
	 * @author skhan
	 * */
	public BooleanExpression teamnameEq(String membername) {
		return StringUtils.hasText(membername) ? team.teamName.eq(membername) : null;
	}
	/**
	 * @description activateStatus = request
	 * @author skhan
	 * */
	public BooleanExpression activatestatusEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? team.activateStatus.eq(activateStatus) : null;
	}
	/**
	 * @description city = request
	 * @author skhan
	 * */
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? team.city.eq(city) : null;
	}
}

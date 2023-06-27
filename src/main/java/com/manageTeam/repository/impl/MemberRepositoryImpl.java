package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QMember.member;
import static com.manageTeam.entity.QTeam.team;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.dto.MemberConditionDto;
import com.manageTeam.dto.MemberDto;
import com.manageTeam.repository.MemberRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;
	
	@Override
	public Page<MemberDto.Info> findAllByCondition(MemberConditionDto memberConditionDto, Pageable pageable) {
		
		List<MemberDto.Info> results = queryFactory
				.select(Projections.constructor(MemberDto.Info.class,
						member.memberId,
						member.membername,
						member.age,
						member.birth,
						member.address,
						member.position,
						team.teamName
						))
				.from(member)
				.join(member.team, team)
				.where(
						usernameEq(memberConditionDto.getMemberName()),
						teamEq(memberConditionDto.getTeamName()),
						ageGoe(memberConditionDto.getAgeGoe()),
						ageLoe(memberConditionDto.getAgeLoe()),
						cityEq(memberConditionDto.getTeamName())
						)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> totalQuery = queryFactory
				.select(member.count())
				.from(member)
				.join(member.team, team)
				.where(
						usernameEq(memberConditionDto.getMemberName()),
						teamEq(memberConditionDto.getTeamName()),
						ageGoe(memberConditionDto.getAgeGoe()),
						ageLoe(memberConditionDto.getAgeLoe()),
						cityEq(memberConditionDto.getTeamName())
						);
		
		return PageableExecutionUtils.getPage(results, pageable, totalQuery::fetchOne);
	}
	
	public BooleanExpression usernameEq(String membername) {
		return StringUtils.hasText(membername) ? member.membername.eq(membername) : null;
	}
	public BooleanExpression teamEq(String teamName) {
		return StringUtils.hasText(teamName) ? member.team.teamName.eq(teamName) : null;
	}
	public BooleanExpression ageGoe(Integer age) {
		return age!=null ? member.age.goe(age): null;
	}
	public BooleanExpression ageLoe(Integer age) {
		return age!=null ? member.age.loe(age) : null;
	}
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? member.address.city.eq(city) : null;
	}

}

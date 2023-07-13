package com.manageTeam.domain.member.repository.impl;

import static com.manageTeam.domain.member.entity.QMember.member;
import static com.manageTeam.domain.team.entity.QTeam.team;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.domain.member.dto.MemberConditionDto;
import com.manageTeam.domain.member.dto.MemberDto;
import com.manageTeam.domain.member.repository.MemberRepositoryCustom;
import com.manageTeam.global.dto.AddressDto;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
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
		Predicate predicate = new BooleanBuilder()
				.and(membernameEq(memberConditionDto.getMemberName()))
	            .and(teamEq(memberConditionDto.getTeamName()))
	            .and(ageGoe(memberConditionDto.getAgeGoe()))
	            .and(ageLoe(memberConditionDto.getAgeLoe()))
	            .and(cityEq(memberConditionDto.getTeamName()))
	            .and(activateStatusEq(memberConditionDto.getActivateStatus()));
		
		List<MemberDto.Info> results = queryFactory
				.select(Projections.constructor(MemberDto.Info.class,
						member.memberId,
						member.membername,
						member.age,
						member.rsdntRgnmb,
						member.phone,
						Projections.constructor(AddressDto.class,
								member.address.city,
								member.address.street,
								member.address.zipcode
								),
						team.teamName,
						member.position
						))
				.from(member)
				.join(member.team, team)
				.where(predicate)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> totalQuery = queryFactory
				.select(member.count())
				.from(member)
				.join(member.team, team)
				.where(predicate);
		
		return PageableExecutionUtils.getPage(results, pageable, totalQuery::fetchOne);
	}
	
	public BooleanExpression membernameEq(String membername) {
		return StringUtils.hasText(membername) ? member.membername.eq(membername) : null;
	}
	public BooleanExpression teamEq(String teamName) {
		return StringUtils.hasText(teamName) ? member.team.teamName.eq(teamName) : null;
	}
	public BooleanExpression ageGoe(Integer age) {
		return age!=0 ? member.age.goe(age): null;
	}
	public BooleanExpression ageLoe(Integer age) {
		return age!=0 ? member.age.loe(age) : null;
	}
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? member.address.city.eq(city) : null;
	}
	public BooleanExpression activateStatusEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? member.activateStatus.eq(activateStatus) : null;
	}

	@Override
	public boolean existsByRsdntRgnmb(String rsdntRgnmb) {
		return queryFactory
				.select(member.count())
				.from(member)
				.where(
						member.activateStatus.eq(ActivateStatus.YES),
						member.rsdntRgnmb.eq(rsdntRgnmb)
						)
				.fetchOne()<1;
	}
}

package com.manageTeam.domain.member.repository.impl;

import static com.manageTeam.domain.member.entity.QMember.member;
import static com.manageTeam.domain.team.entity.QTeam.team;

import java.util.List;

import com.manageTeam.domain.member.dto.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.domain.member.dto.MemberConditionDto;
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
	
	/**
	 * @description 팀원의 목록을 조회한다.
	 * @author skhan
	 */
	@Override
	public Page<MemberResponse.Info> findAllByCondition(MemberConditionDto memberConditionDto, Pageable pageable) {
		Predicate predicate = new BooleanBuilder()
				.and(membernameEq(memberConditionDto.getMemberName()))
	            .and(teamEq(memberConditionDto.getTeamName()))
	            .and(ageGoe(memberConditionDto.getAgeGoe()))
	            .and(ageLoe(memberConditionDto.getAgeLoe()))
	            .and(cityEq(memberConditionDto.getTeamName()))
	            .and(activateStatusEq(memberConditionDto.getActivateStatus()));
		
		List<MemberResponse.Info> results = queryFactory
				.select(Projections.constructor(MemberResponse.Info.class,
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
	/**
	 * @description membername = request
	 * @author skhan
	 */
	public BooleanExpression membernameEq(String membername) {
		return StringUtils.hasText(membername) ? member.membername.eq(membername) : null;
	}
	/**
	 * @description teamName = request
	 * @author skhan
	 */
	public BooleanExpression teamEq(String teamName) {
		return StringUtils.hasText(teamName) ? member.team.teamName.eq(teamName) : null;
	}
	/**
	 * @description age <= request
	 * @author skhan
	 */
	public BooleanExpression ageGoe(Integer age) {
		return age!=0 ? member.age.goe(age): null;
	}
	/**
	 * @description age >= request
	 * @author skhan
	 */
	public BooleanExpression ageLoe(Integer age) {
		return age!=0 ? member.age.loe(age) : null;
	}
	/**
	 * @description city = request
	 * @author skhan
	 */
	public BooleanExpression cityEq(String city) {
		return StringUtils.hasText(city) ? member.address.city.eq(city) : null;
	}
	/**
	 * @description activateStatus = request
	 * @author skhan
	 */
	public BooleanExpression activateStatusEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? member.activateStatus.eq(activateStatus) : null;
	}

	/**
	 * @description 이미 등록된 주민번호 인지 체크한다.
	 * @author skhan
	 */
	@Override
	public boolean existsByRsdntRgnmb(String rsdntRgnmb) {
		Long count = queryFactory
				.select(member.count())
				.from(member)
				.where(
						member.activateStatus.eq(ActivateStatus.YES),
						member.rsdntRgnmb.eq(rsdntRgnmb)
						)
				.fetchOne();

		return count != null && count <= 0;
	}
}

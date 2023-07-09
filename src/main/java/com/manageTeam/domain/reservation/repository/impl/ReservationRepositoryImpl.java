package com.manageTeam.domain.reservation.repository.impl;

import static com.manageTeam.entity.QGym.gym;
import static com.manageTeam.entity.QReservation.reservation;
import static com.manageTeam.entity.QReservationTeam.reservationTeam;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.dto.ReservationDto;
import com.manageTeam.domain.reservation.repository.ReservationRepositoryCustom;
import com.manageTeam.entity.ActivateStatus;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto.ListCondition conditionDto, Pageable pageable) {
		
		Predicate predicate = gymnameLike(conditionDto.getGymName())
						.and(startGoe(conditionDto.getDateGoe()))
						.and(endLoe(conditionDto.getDateLoe()))
						.and(cityLike(conditionDto.getCity()))
						.and(activateEq(conditionDto.getActivateStatus()));
		
		List<ReservationDto.Info> results = queryFactory
				.select(Projections.bean(ReservationDto.Info.class, 
						reservation.reservationId,
						reservation.totalTeamCnt,
						reservationTeam.count().as("joinTeam"),
						gym.gymId,
						gym.gymName,
						gym.address,
						reservation.startTime,
						reservation.endTime
						))
				.from(reservation)
				.join(reservation.gym, gym)
				.leftJoin(reservation.reservationTeams, reservationTeam)
				.where(predicate)
				.groupBy(reservation.reservationId)
				.having(reservation.reservationId.isNotNull())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(reservation.count())
				.from(reservation)
				.join(reservation.gym, gym)
				.leftJoin(reservation.reservationTeams, reservationTeam)
				.where(predicate)
				.groupBy(reservation.reservationId)
				.having(reservation.reservationId.isNotNull());
				
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
	
	@Override
	public boolean findReservationByDate(ReservationConditionDto.DateCondition condition, Long teamId) {
		
		return queryFactory
				.select(reservationTeam.count())
				.from(reservationTeam)
				.join(reservationTeam.reservation, reservation)
				.where(
						reservationTeam.team.teamId.eq(teamId),
						reservationTeam.activateStatus.eq(ActivateStatus.YES),
						startLoe(condition.getEndDate()),
						endGoe(condition.getStartDate())
						)
				.fetchOne() < 0;
	}
	
	public BooleanExpression gymnameLike(String gymname) {
		return StringUtils.hasText(gymname) ? gym.gymName.contains(gymname) : null;
	}
	public BooleanExpression startGoe(LocalDateTime dateGoe) {
		return dateGoe!=null ? reservation.startTime.goe(dateGoe) : null;
	}
	public BooleanExpression endLoe(LocalDateTime dateLoe) {
		return dateLoe!=null ? reservation.endTime.loe(dateLoe) : null;
	}
	public BooleanExpression startLoe(LocalDateTime dateGoe) {
		return dateGoe!=null ? reservation.startTime.loe(dateGoe) : null;
	}
	public BooleanExpression endGoe(LocalDateTime dateLoe) {
		return dateLoe!=null ? reservation.endTime.goe(dateLoe) : null;
	}
	public BooleanExpression cityLike(String city) {
		return StringUtils.hasText(city) ? gym.address.city.contains(city) : null;
	}
	public BooleanExpression activateEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? reservation.activateStatus.eq(activateStatus) : null;
	}

	@Override
	public Long findTeamCntReservation(Long reservationId) {
		Long count = queryFactory
				.select(reservationTeam.count())
				.from(reservation)
				.innerJoin(reservation.reservationTeams, reservationTeam)
				.where(reservationTeam.activateStatus.eq(ActivateStatus.YES))
				.fetchOne();
		
		return count;
	}
}

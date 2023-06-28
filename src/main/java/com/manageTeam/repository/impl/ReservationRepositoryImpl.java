package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QGym.gym;
import static com.manageTeam.entity.QReservation.reservation;
import static com.manageTeam.entity.QReservationTeam.reservationTeam;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import com.manageTeam.dto.ReservationConditionDto;
import com.manageTeam.dto.ReservationDto;
import com.manageTeam.repository.ReservationRepositoryCustom;
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
		
		List<ReservationDto.Info> results = queryFactory
				.select(Projections.constructor(ReservationDto.Info.class, 
						reservation.reservationId,
						reservation.totalTeamCnt,
						reservationTeam.count(),
						gym.gymId,
						gym.gymName,
						gym.address,
						reservation.startTime,
						reservation.endTime
						))
				.from(reservation)
				.join(reservation.gym, gym)
				.leftJoin(reservation.reservationTeams, reservationTeam)
				.where(
						gymnameLike(conditionDto.getGymName()),
						dateGoe(conditionDto.getDateGoe()),
						dateLoe(conditionDto.getDateLoe()),
						cityLike(conditionDto.getCity())
						)
				.groupBy(reservation.reservationId)
				.having(reservation.reservationId.isNotNull())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(reservation.count())
				.from(reservation)
				.join(reservation.gym, gym)
				.leftJoin(reservation.reservationTeams, reservationTeam)
				.where(
						gymnameLike(conditionDto.getGymName()),
						dateGoe(conditionDto.getDateGoe()),
						dateLoe(conditionDto.getDateLoe()),
						cityLike(conditionDto.getCity())
						)
				.groupBy(reservation.reservationId)
				.having(reservation.reservationId.isNotNull());
				
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
	
	@Override
	public boolean findReservationByDate(ReservationConditionDto.DateCondition condition) {
		
		boolean check = queryFactory
				.select(reservationTeam.count())
				.from(reservationTeam)
				.fetch();
		
		return false;
	}
	
	public BooleanExpression gymnameLike(String gymname) {
		return StringUtils.hasText(gymname) ? gym.gymName.contains(gymname) : null;
	}
	public BooleanExpression dateGoe(LocalDateTime dateGoe) {
		return dateGoe!=null ? reservation.startTime.goe(dateGoe) : null;
	}
	public BooleanExpression dateLoe(LocalDateTime dateLoe) {
		return dateLoe!=null ? reservation.endTime.loe(dateLoe) : null;
	}
	public BooleanExpression cityLike(String city) {
		return StringUtils.hasText(city) ? gym.address.city.contains(city) : null;
	}
}

package com.manageTeam.domain.reservationTeam.repository.impl;

import static com.manageTeam.domain.reservation.entity.QReservation.reservation;
import static com.manageTeam.domain.gym.entity.QGym.gym;
import static com.manageTeam.domain.reservationTeam.entity.QReservationTeam.reservationTeam;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.manageTeam.domain.reservationTeam.dto.ReservationTeamDto;
import com.manageTeam.domain.reservationTeam.repository.ReservationTeamRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationTeamRepositoryImpl implements ReservationTeamRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<ReservationTeamDto.Info> findAllByTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable) {
		
		List<ReservationTeamDto.Info> results = queryFactory
				.select(Projections.bean(ReservationTeamDto.Info.class,
						reservationTeam.reservationTeamId,
						reservationTeam.team.teamId,
						reservation.reservationId,
						gym.gymId,
						gym.gymName,
						gym.address.city,
						reservationTeam.activateStatus,
						reservation.startTime,
						reservation.endTime
						))
				.from(reservationTeam)
				.join(reservationTeam.reservation, reservation)
				.join(reservation.gym, gym)
				.where(
						reservationTeam.team.teamId.eq(teamId),
						activateStatus(activateStatus))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(reservationTeam.reservationTeamId.count())
				.from(reservationTeam)
				.join(reservationTeam.reservation, reservation)
				.join(reservation.gym, gym)
				.where(reservationTeam.team.teamId.eq(teamId),
					   activateStatus(activateStatus));
		
		
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
	
	public BooleanExpression activateStatus(ActivateStatus activateStatus) {
		return activateStatus!=null ? reservationTeam.activateStatus.eq(activateStatus) : null;
	}
}

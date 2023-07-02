package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QReservationTeam.reservationTeam;
import static com.manageTeam.entity.QReservation.reservation;
import static com.manageTeam.entity.QGym.gym;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.repository.ReservationTeamRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationTeamRepositoryImpl implements ReservationTeamRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public Page<ReservationTeamDto.Info> findAllByTeam(Long teamId, Pageable pageable) {
		
		List<ReservationTeamDto.Info> results = queryFactory
				.select(Projections.constructor(ReservationTeamDto.Info.class,
						reservationTeam.reservationTeamId,
						reservationTeam.team.teamId,
						reservation.reservationId,
						gym.gymId,
						gym.gymName,
						gym.address.city,
						reservation.startTime,
						reservation.endTime
						))
				.from(reservationTeam)
				.join(reservationTeam.reservation, reservation)
				.join(reservation.gym, gym)
				.where(reservationTeam.team.teamId.eq(teamId))
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		JPAQuery<Long> countQuery = queryFactory
				.select(reservationTeam.reservationTeamId.count())
				.from(reservationTeam)
				.join(reservationTeam.reservation, reservation)
				.join(reservation.gym, gym)
				.where(reservationTeam.team.teamId.eq(teamId));
		
		
		return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchOne);
	}
}
package com.manageTeam.domain.reservationTeam.repository.impl;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservationTeam.dto.ReservationTeamResponse;
import com.manageTeam.domain.reservationTeam.repository.ReservationTeamRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.manageTeam.domain.gym.entity.QGym.gym;
import static com.manageTeam.domain.reservation.entity.QReservation.reservation;
import static com.manageTeam.domain.reservationTeam.entity.QReservationTeam.reservationTeam;

@RequiredArgsConstructor
public class ReservationTeamRepositoryImpl implements ReservationTeamRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;


	/**
	 * @description 해당 시간에 이미 등록된 체육관이 있는지 체크한다.
	 * @author skhan
	 * */
	@Override
	public boolean findReservationByDate(ReservationConditionDto.DateCondition condition, Long teamId) {
		Long count = queryFactory
			.select(reservationTeam.count())
			.from(reservationTeam)
			.join(reservationTeam.reservation, reservation)
			.where(
				reservationTeam.team.teamId.eq(teamId),
				reservationTeam.activateStatus.eq(ActivateStatus.YES),
				startLoe(condition.getEndDate()),
				endGoe(condition.getStartDate())
			)
			.fetchOne();

		return count != null && count <= 0;
	}

	/**
	 * @description 특정팀이 예약한 체육관 목록을 조회한다.
	 * @author skhan
	 * */
	@Override
	public Page<ReservationTeamResponse.Info> findAllByTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable) {
		
		List<ReservationTeamResponse.Info> results = queryFactory
				.select(Projections.bean(ReservationTeamResponse.Info.class,
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
	
	/**
	 * @description activateStatus = request
	 * @author skhan
	 * */
	public BooleanExpression activateStatus(ActivateStatus activateStatus) {
		return activateStatus!=null ? reservationTeam.activateStatus.eq(activateStatus) : null;
	}

	/**
	 * @description startDate >= request
	 * @author skhan
	 */
	public BooleanExpression startLoe(LocalDateTime dateGoe) {
		return dateGoe!=null ? reservation.startTime.loe(dateGoe) : null;
	}
	/**
	 * @description endDate <= request
	 * @author skhan
	 */
	public BooleanExpression endGoe(LocalDateTime dateLoe) {
		return dateLoe!=null ? reservation.endTime.goe(dateLoe) : null;
	}
}

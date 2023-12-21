package com.manageTeam.domain.reservation.repository.impl;

import com.manageTeam.domain.reservation.dto.ReservationConditionDto;
import com.manageTeam.domain.reservation.dto.ReservationResponse;
import com.manageTeam.domain.reservation.repository.ReservationRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.manageTeam.domain.gym.entity.QGym.gym;
import static com.manageTeam.domain.reservation.entity.QReservation.reservation;
import static com.manageTeam.domain.reservationTeam.entity.QReservationTeam.reservationTeam;

@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	/**
	 * @description 등록된 체육관 예약목록을 조회한다.
	 * @author skhan
	 * */
	@Override
	public Page<ReservationResponse.Info> findAllByCondition(ReservationConditionDto.ListCondition conditionDto, Pageable pageable) {
		
		Predicate predicate = gymnameLike(conditionDto.getGymName())
						.and(startGoe(conditionDto.getStartDate()))
						.and(endLoe(conditionDto.getEndDate()))
						.and(cityLike(conditionDto.getCity()))
						.and(activateEq(conditionDto.getActivateStatus()));

		List<ReservationResponse.Info> results = queryFactory
				.select(Projections.bean(ReservationResponse.Info.class,
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

		return count != null && count < 0;
	}
	/**
	 * @description gymName = request
	 * @author skhan
	 */
	public BooleanExpression gymnameLike(String gymname) {
		return StringUtils.hasText(gymname) ? gym.gymName.contains(gymname) : null;
	}
	/**
	 * @description startDate <= request
	 * @author skhan
	 */
	public BooleanExpression startGoe(LocalDateTime dateGoe) {
		return dateGoe!=null ? reservation.startTime.goe(dateGoe) : null;
	}
	/**
	 * @description endDate >= request
	 * @author skhan
	 */
	public BooleanExpression endLoe(LocalDateTime dateLoe) {
		return dateLoe!=null ? reservation.endTime.loe(dateLoe) : null;
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
	/**
	 * @description city = request
	 * @author skhan
	 */
	public BooleanExpression cityLike(String city) {
		return StringUtils.hasText(city) ? gym.address.city.contains(city) : null;
	}
	/**
	 * @description activateStatus = request
	 * @author skhan
	 */
	public BooleanExpression activateEq(ActivateStatus activateStatus) {
		return activateStatus!=null ? reservation.activateStatus.eq(activateStatus) : null;
	}
	
	/**
	 * @description 체육관에 예약 되어 있는 팀의 수를 조회한다.
	 * @author skhan
	 */
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

package com.manageTeam.domain.gym.repository.impl;

import static com.manageTeam.domain.gym.entity.QGym.gym;

import com.manageTeam.domain.gym.repository.GymRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GymRepositoryImpl implements GymRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	/**
	 * @description 동일한 체육관이 등록되어있는지 체크한다.
	 * @author skhan
	 */
	@Override
	public boolean checkGymExist(String zipCode, String name) {
		Long count = queryFactory
				.select(gym.count())
				.from(gym)
				.where(
					gym.address.zipcode.eq(zipCode),
					gym.gymName.eq(name),
					gym.activateStatus.eq(ActivateStatus.YES)
				)
				.fetchOne();

		return count != null && count < 0;
	}

}

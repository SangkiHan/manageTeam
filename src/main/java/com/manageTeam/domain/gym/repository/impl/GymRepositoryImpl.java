package com.manageTeam.domain.gym.repository.impl;

import static com.manageTeam.domain.gym.entity.QGym.gym;

import com.manageTeam.domain.gym.repository.GymRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GymRepositoryImpl implements GymRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public boolean checkGymExist(String zipCode, String name) {
		return queryFactory
				.select(gym.count())
				.from(gym)
				.where(
					gym.address.zipcode.eq(zipCode),
					gym.gymName.eq(name)
				)
				.fetchOne() > 0;
	}

}

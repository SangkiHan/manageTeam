package com.manageTeam.domain.user.repository.impl;

import com.manageTeam.domain.user.repository.UserRepositoryCustom;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.security.CustumUserDetails;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.manageTeam.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	@Override
	public boolean existsByRsdntRgnmb(String rsdntRgnmb) {
		Long count = queryFactory
				.select(user.count())
				.from(user)
				.where(
						user.activateStatus.eq(ActivateStatus.YES),
						user.rsdntRgnmb.eq(rsdntRgnmb)
						)
				.fetchOne();

		return count != null && count <= 0;
	}

	@Override
	public CustumUserDetails findUser(String userId) {
		return queryFactory
			.select(Projections.bean(CustumUserDetails.class,
				user.userId.as("username"),
				user.password
			))
			.from(user)
			.where(user.userId.eq(userId))
			.fetchOne();
	}
}

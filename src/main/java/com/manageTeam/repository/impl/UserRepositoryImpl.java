package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QUser.user;

import com.manageTeam.dto.UserDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.repository.UserRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	
	@Override
	public UserDto.Info findUserInfo(String userId) {
		UserDto.Info results = queryFactory
				.select(Projections.bean(UserDto.Info.class,
						user.userId,
						user.team.teamId,
						user.team.teamName,
						user.password,
						user.username,
						user.rsdntRgnmb,
						user.phone,
						user.address,
						user.auth,
						user.activateStatus
						))
				.from(user)
				.where(user.userId.eq(userId))
				.fetchOne();
		
		return results;
	}

	@Override
	public boolean existsByRsdntRgnmb(String rsdntRgnmb) {
		return queryFactory
				.select(user.count())
				.from(user)
				.where(user.activateStatus.eq(ActivateStatus.YES))
				.fetchOne() < 0;
	}
}

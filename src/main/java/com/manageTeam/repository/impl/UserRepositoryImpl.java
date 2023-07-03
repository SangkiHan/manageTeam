package com.manageTeam.repository.impl;

import static com.manageTeam.entity.QUser.user;

import com.manageTeam.dto.AddressDto;
import com.manageTeam.dto.UserDto;
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
				.select(Projections.constructor(UserDto.Info.class,
						user.userId,
						user.team.teamId,
						user.team.teamName,
						user.password,
						user.username,
						user.rsdntRgnmb,
						user.phone,
						Projections.constructor(AddressDto.class, 
								user.address
								),
						user.auth,
						user.activateStatus
						))
				.from(user)
				.where(user.userId.eq(userId))
				.fetchOne();
		
		return results;
	}

}

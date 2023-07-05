package com.manageTeam.repository;

import java.util.Optional;

import com.manageTeam.dto.UserDto;

public interface UserRepositoryCustom {
	public Optional<UserDto.Info> findUserInfo(String userId);
	public boolean existsByRsdntRgnmb(String rsdntRgnmb);
}

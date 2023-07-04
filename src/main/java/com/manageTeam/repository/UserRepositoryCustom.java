package com.manageTeam.repository;

import com.manageTeam.dto.UserDto;

public interface UserRepositoryCustom {
	public UserDto.Info findUserInfo(String userId);
	public boolean existsByRsdntRgnmb(String rsdntRgnmb);
}

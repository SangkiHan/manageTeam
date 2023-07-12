package com.manageTeam.domain.gym.repository;

import com.manageTeam.domain.gym.dto.GymDto;

public interface GymRepositoryCustom {
	public boolean checkGymExist(String zipCode, String name);
}

package com.manageTeam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.dto.ReservationConditionDto;
import com.manageTeam.dto.ReservationDto;

public interface ReservationRepositoryCustom {
	public Page<ReservationDto.Info> findAllByCondition(ReservationConditionDto conditionDto, Pageable pageable);
}

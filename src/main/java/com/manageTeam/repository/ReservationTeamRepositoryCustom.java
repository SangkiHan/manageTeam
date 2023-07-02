package com.manageTeam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.entity.ActivateStatus;

public interface ReservationTeamRepositoryCustom {
	public Page<ReservationTeamDto.Info> findAllByTeam(Long teamId, ActivateStatus activateStatus,  Pageable pageable);
}

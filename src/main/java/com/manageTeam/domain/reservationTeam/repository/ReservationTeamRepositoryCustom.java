package com.manageTeam.domain.reservationTeam.repository;

import com.manageTeam.domain.reservationTeam.dto.ReservationTeamResponse;
import com.manageTeam.global.entity.ActivateStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationTeamRepositoryCustom {
	public Page<ReservationTeamResponse.Info> findAllByTeam(Long teamId, ActivateStatus activateStatus, Pageable pageable);
}

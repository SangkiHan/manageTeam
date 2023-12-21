package com.manageTeam.domain.team.repository;

import com.manageTeam.domain.team.dto.TeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.team.dto.TeamConditionDto;

public interface TeamRepositoryCustom {
	public Page<TeamResponse.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable);
	public TeamResponse.DetailInfo findOne(Long teamId);
}

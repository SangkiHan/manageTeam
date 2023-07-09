package com.manageTeam.domain.team.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamDto;

public interface TeamRepositoryCustom {
	public Page<TeamDto.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable);
	public TeamDto.Info findTeamInfo(Long teamId);
}

package com.manageTeam.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.dto.TeamConditionDto;
import com.manageTeam.dto.TeamDto;
import com.manageTeam.entity.Team;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	public void save(TeamDto.Save request) {
		Team team = new Team(request);
		teamRepository.save(team);
	}
	
	public TeamDto.Info findById(TeamDto.Id request) {
		Team result = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("해당 팀 데이터가 없음"));
		
		TeamDto.Info team = new TeamDto.Info(result);
		return team;
	}
	
	public Page<TeamDto.Info> findAllByCondition (TeamConditionDto conditionDto, Pageable pageable){
		return teamRepository.findAllByCondition(conditionDto, pageable);
	}
}

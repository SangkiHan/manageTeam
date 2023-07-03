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
	
	public void activate(TeamDto.Status request) {
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("TEA0001","해당 팀이 존재하지 않습니다. 관리자에게 문의 부탁드립니다."));
		team.setStatus(request.getActivateStatus());
	}
	
	public TeamDto.Info findById(TeamDto.TeamId request) {
		return  teamRepository.findTeamInfo(request.getTeamId());
	}
	
	public Page<TeamDto.Info> findAllByCondition (TeamConditionDto conditionDto, Pageable pageable){
		return teamRepository.findAllByCondition(conditionDto, pageable);
	}
}

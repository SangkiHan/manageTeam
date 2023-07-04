package com.manageTeam.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.dto.TeamConditionDto;
import com.manageTeam.dto.TeamDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Team;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	/**
	 * @api /api/team/v1/save
	 * @description 팀 저장 및 수정한다.
	 * @throws Exception
	 * @author skhan
	 * */
	public void save(TeamDto.Save request) {
		Team team = new Team(request);
		team.setStatus(ActivateStatus.YES);
		teamRepository.save(team);
	}
	
	/**
	 * @api /api/team/v1/status
	 * @description 팀의 활성화 상태를 번경한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	public void status(TeamDto.Status request) {
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("TEA0001","해당 팀이 존재하지 않습니다. 관리자에게 문의 부탁드립니다."));
		team.setStatus(request.getActivateStatus());
	}
	
	/**
	 * @api /api/team/v1/findById
	 * @description 팀상세를 조회한다.
	 * @throws Exception
	 * @author skhan
	 * */
	public TeamDto.Info findById(TeamDto.TeamId request) {
		return  teamRepository.findTeamInfo(request.getTeamId());
	}
	
	/**
	 * @api /api/team/v1/findAll
	 * @description 팀목록을 조회한다.
	 * @throws Exception
	 * @author skhan
	 * */
	public Page<TeamDto.Info> findAllByCondition (TeamConditionDto conditionDto, Pageable pageable){
		return teamRepository.findAllByCondition(conditionDto, pageable);
	}
}

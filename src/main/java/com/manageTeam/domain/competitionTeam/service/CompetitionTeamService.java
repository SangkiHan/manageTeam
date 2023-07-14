package com.manageTeam.domain.competitionTeam.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competition.repository.CompetitionRepository;
import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamContidtionDto;
import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamDto;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.domain.competitionTeam.repository.CompetitionTeamRepository;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CompetitionTeamService {
	
	private final CompetitionTeamRepository competitionTeamRepository;
	private final TeamRepository teamRepository;
	private final CompetitionRepository competitionRepository;
	
	/**
	 * @api /api/competitionTeam/v1/save
	 * @description 대회 참가 신청을 한다.
	 * @author skhan
	 */
	public void save(CompetitionTeamDto.Save request) {
		Long competitionId = request.getCompetitionId();
		
		CompetitionTeam competitionTeam = new CompetitionTeam(request);
		
		//대회를 조회한다.
		Competition competition = competitionRepository.findById(competitionId)
				.orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_UNKNOWN));
		
		//대회 참가 신청을 한 팀의 수를 조회한다. 
		Long count = competitionRepository.regTeamCount(competitionId);
		if(competition.getTeamCnt()==count) {
			throw new GlobalException(ErrorCode.COMPETITION_DEADLINE);
		}
		
		CompetitionTeamContidtionDto.checkDate condiContidtionDto = 
				new CompetitionTeamContidtionDto.checkDate(
						request.getTeamId(),
						competition.getStartDate(),
						competition.getEndDate()
						);
		//해당 날짜에 이미 등록되어있는 대회여부를 조회한다.
		if(!competitionTeamRepository.checkCompetitionTeamDate(condiContidtionDto)) {
			throw new GlobalException(ErrorCode.COMPETITION_TEAM_DATE);
		}
		//대회세팅
		competitionTeam.createCompetition(competition);
		//참가하려는 팀을 조회한다.
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException(ErrorCode.TEAM_UNKNOWN));
		//팀 세팅
		competitionTeam.createTeam(team);
		
		competitionTeamRepository.save(competitionTeam);
	}
	
	/**
	 * @api /api/competitionTeam/v1/cancel
	 * @description 대회 참가를 취소한다.
	 * @author skhan
	 */
	public void cancel(CompetitionTeamDto.CompetitionTeamId request) {
		//취소하려는 팀 조회
		CompetitionTeam competitionTeam = competitionTeamRepository.findById(request.getCompetitionTeamId())
				.orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_TEAM_UNKNOWN));
		
		competitionTeam.cancel();
	}
}

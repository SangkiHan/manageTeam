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
	
	public void save(CompetitionTeamDto.Save request) {
		CompetitionTeam competitionTeam = new CompetitionTeam(request);
		
		Competition competition = competitionRepository.findById(request.getCompetitionId())
				.orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_UNKNOWN));
		
		CompetitionTeamContidtionDto.checkDate condiContidtionDto = 
				new CompetitionTeamContidtionDto.checkDate(
						request.getTeamId(),
						competition.getStartDate(),
						competition.getEndDate()
						);
		
		if(!competitionTeamRepository.checkCompetitionTeamDate(condiContidtionDto)) {
			throw new GlobalException(ErrorCode.COMPETITION_TEAM_DATE);
		}
		
		competitionTeam.createCompetition(competition);
		
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException(ErrorCode.TEAM_UNKNOWN));
		competitionTeam.createTeam(team);
		
		competitionTeamRepository.save(competitionTeam);
	}
	
	public void cancel(CompetitionTeamDto.CompetitionTeamId request) {
		CompetitionTeam competitionTeam = competitionTeamRepository.findById(request.getCompetitionTeamId())
				.orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_TEAM_UNKNOWN));
		
		competitionTeam.cancel();
	}
}

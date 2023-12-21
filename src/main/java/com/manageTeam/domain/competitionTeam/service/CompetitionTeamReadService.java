package com.manageTeam.domain.competitionTeam.service;

import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamContidtionDto;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.domain.competitionTeam.repository.CompetitionTeamRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompetitionTeamReadService {

    private final CompetitionTeamRepository competitionTeamRepository;

    public void checkCompetitionTeamDate(CompetitionTeamContidtionDto.checkDate condiContidtionDto){
        if(!competitionTeamRepository.checkCompetitionTeamDate(condiContidtionDto)) {
            throw new GlobalException(ErrorCode.COMPETITION_TEAM_DATE);
        }
    }

    public CompetitionTeam findById(Long competitionTeamId){
        return competitionTeamRepository.findById(competitionTeamId)
            .orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_TEAM_UNKNOWN));
    }

}

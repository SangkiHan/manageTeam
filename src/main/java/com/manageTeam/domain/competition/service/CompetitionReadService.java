package com.manageTeam.domain.competition.service;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competition.repository.CompetitionRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompetitionReadService {

    private final CompetitionRepository competitionRepository;

    public Competition findById(Long competitionId){
        return competitionRepository.findById(competitionId)
            .orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_UNKNOWN));
    }

    public void checkCompetitionGym(CompetitionConditionDto.DateCheck conditionDto){
        if(!competitionRepository.checkCompetitionGym(conditionDto)) {
            throw new GlobalException(ErrorCode.COMPETITION_DATE);
        }
    }
}

package com.manageTeam.domain.competition.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competition.repository.CompetitionRepository;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor     
@Transactional
public class CompetitionService {
	
	private final CompetitionRepository competitionRepository;
	private final GymRepository gymRepository;
	
	/**
	 * @api /api/competition/v1/save
	 * @description 대회를 등록 및 수정한다.
	 * @author skhan
	 */
	public void save(CompetitionDto.Save request) {
		CompetitionConditionDto.DateCheck conditionDto = new CompetitionConditionDto.DateCheck(request);
		if(!competitionRepository.checkCompetitionGym(conditionDto)) {
			throw new GlobalException(ErrorCode.COMPETITION_DATE);
		}
		
		Competition competition = new Competition(request);
		Gym gym = gymRepository.findById(request.getGymId())
				.orElseThrow(() -> new GlobalException(ErrorCode.GYM_UNKNOWN));
		competition.createCompetition(gym);
		competitionRepository.save(competition);
	}
	
	/**
	 * @api /api/competition/v1/findOne
	 * @description 대회를 상세조회한다.
	 * @author skhan
	 */
	public CompetitionDto.DetailInfo findOne(Long competitionId) {
		Competition competition = competitionRepository.findOne(competitionId)
				.orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_UNKNOWN));
		
		CompetitionDto.DetailInfo result = new CompetitionDto.DetailInfo(competition);
		
		return result;
	}
	
	/**
	 * @api /api/competition/v1/findAll
	 * @description 현재 등록되어 있는 대회목록을 조회한다.
	 * @author skhan
	 */
	public Page<CompetitionDto.Info> findAllByCondition(CompetitionConditionDto request, Pageable pageable){
		return competitionRepository.findAllByCondition(request, pageable);
	}
	
	/**
	 * @api /api/competition/v1/cancel
	 * @description 대회개최를 취소한다.
	 * @author skhan
	 */
	public void cancel(CompetitionDto.CompetitionId request) {
		Long competitionId = request.getCompetitionId();
		Competition competition = competitionRepository.findOne(competitionId)
				.orElseThrow(() -> new GlobalException(ErrorCode.COMPETITION_UNKNOWN));
		
		competition.cancel();
	}

}

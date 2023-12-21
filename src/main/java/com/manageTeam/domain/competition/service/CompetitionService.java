package com.manageTeam.domain.competition.service;

import com.manageTeam.domain.competition.dto.CompetitionConditionDto;
import com.manageTeam.domain.competition.dto.CompetitionRequest;
import com.manageTeam.domain.competition.dto.CompetitionResponse;
import com.manageTeam.domain.competition.repository.CompetitionRepository;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.service.GymReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor     
@Transactional
public class CompetitionService {
	
	private final CompetitionRepository competitionRepository;
	private final CompetitionReadService competitionReadService;
	private final GymReadService gymReadService;
	
	/**
	 * @api /api/competition/v1/save
	 * @description 대회를 등록 및 수정한다.
	 * @author skhan
	 */
	public void save(CompetitionRequest.Save request) {
		CompetitionConditionDto.DateCheck conditionDto = CompetitionConditionDto.DateCheck.builder()
			.gymId(request.getGymId())
			.startDate(request.getStartDate())
			.endDate(request.getEndDate())
			.build();

		competitionReadService.checkCompetitionGym(conditionDto);
		
		Gym gym = gymReadService.findById(request.getGymId());

		competitionRepository.save(request.toEntity(gym));
	}
	
	/**
	 * @api /api/competition/v1/findOne
	 * @description 대회를 상세조회한다.
	 * @author skhan
	 */
	public CompetitionResponse.DetailInfo findOne(Long competitionId) {
		return CompetitionResponse.DetailInfo.of(competitionReadService.findById(competitionId));
	}
	
	/**
	 * @api /api/competition/v1/findAll
	 * @description 현재 등록되어 있는 대회목록을 조회한다.
	 * @author skhan
	 */
	public Page<CompetitionResponse.Info> findAllByCondition(CompetitionConditionDto request, Pageable pageable){
		return competitionRepository.findAllByCondition(request, pageable);
	}
	
	/**
	 * @api /api/competition/v1/cancel
	 * @description 대회개최를 취소한다.
	 * @author skhan
	 */
	public void cancel(CompetitionRequest.CompetitionId request) {
		competitionReadService.findById(request.getCompetitionId()).cancel();
	}

}

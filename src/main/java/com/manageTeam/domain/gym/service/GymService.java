package com.manageTeam.domain.gym.service;

import javax.transaction.Transactional;

import com.manageTeam.domain.gym.dto.GymRequest;
import com.manageTeam.domain.gym.dto.GymResponse;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GymService {
	
	private final GymRepository gymRepository;
	private final GymReadService gymReadService;
	
	/**
	 * @api /api/gym/v1/save
	 * @description 체육관를 저장 및 수정한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public GymResponse.Save save(GymRequest.Save request) {
		gymReadService.checkGymExist(request.getAddress().getZipcode(), request.getGymName());
		return GymResponse.Save.of(gymRepository.save(request.toEntity()));
	}

}

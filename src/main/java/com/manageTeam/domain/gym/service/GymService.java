package com.manageTeam.domain.gym.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.manageTeam.domain.gym.dto.GymDto;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GymService {
	
	private final GymRepository gymRepository;
	
	/**
	 * @api /api/gym/v1/save
	 * @description 체육관를 저장 및 수정한다.
	 * @author skhan
	 * */
	public void save(GymDto.Save request) {
		if(gymRepository.checkGymExist(request.getAddress().getZipcode(), request.getGymName())) {
			throw new GlobalException(ErrorCode.GYM_EXIST);
		}
		Gym gym = new Gym(request);
		gymRepository.save(gym);
	}
	
	
	/**
	 * @api /api/gym/v1/findById
	 * @description 체육관 상세 조회한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public GymDto.Info findById(Long gymId) {
		Gym result = gymRepository.findById(gymId)
				.orElseThrow(() -> new GlobalException(ErrorCode.GYM_UNKNOWN));
		
		GymDto.Info gym = new GymDto.Info(result);
		
		return gym;
	}

}

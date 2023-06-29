package com.manageTeam.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.manageTeam.dto.GymDto;
import com.manageTeam.entity.Gym;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.GymRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GymService {
	
	private final GymRepository gymRepository;
	
	public void save(GymDto.Save request) {
		Gym gym = new Gym(request);
		gymRepository.save(gym);
	}
	
	public GymDto.Info findById(Long gymId) {
		Gym result = gymRepository.findById(gymId)
				.orElseThrow(() -> new GlobalException("GYM0001","해당 체육관 데이터가 없음"));
		
		GymDto.Info gym = new GymDto.Info(result);
		
		return gym;
	}

}

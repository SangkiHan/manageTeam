package com.manageTeam.service;

import org.springframework.stereotype.Service;

import com.manageTeam.dto.GymDto;
import com.manageTeam.entity.Gym;
import com.manageTeam.repository.GymRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GymService {
	
	private final GymRepository gymRepository;
	
	public void save(GymDto.Save request) {
		Gym gym = new Gym(request);
		gymRepository.save(gym);
	}

}

package com.manageTeam.domain.gym.service;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymReadService {

    private final GymRepository gymRepository;

    public Gym findById(Long gymId){
        return gymRepository.findById(gymId)
            .orElseThrow(() -> new GlobalException(ErrorCode.GYM_UNKNOWN));
    }
}

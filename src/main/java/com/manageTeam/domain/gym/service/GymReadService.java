package com.manageTeam.domain.gym.service;

import com.manageTeam.domain.gym.dto.GymResponse;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymReadService {

    private final GymRepository gymRepository;

    public Gym findById(Long gymId){
        return gymRepository.findById(gymId)
            .orElseThrow(() -> new GlobalException(ErrorCode.GYM_UNKNOWN));
    }

    public void checkGymExist(String zipCode, String gymName){
        if(!gymRepository.checkGymExist(zipCode, gymName)) {
            throw new GlobalException(ErrorCode.GYM_EXIST);
        }
    }

    public GymResponse.Info findOne(Long gymId){
        return GymResponse.Info.of(findById(gymId));
    }
}

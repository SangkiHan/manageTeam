package com.manageTeam.domain.user.service;

import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService {

    private final UserRepository userRepository;

    public User findByUserId(String userId){
        return userRepository.findByUserId(userId)
            .orElseThrow(() -> new GlobalException(ErrorCode.USER_UNKNOWN));
    }

}

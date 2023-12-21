package com.manageTeam.domain.user.service;

import com.manageTeam.domain.user.dto.UserResponse;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.global.util.AESUtil;
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
    /**
     * @api /api/user/v1/findUserInfo
     * @description 사용자를 상세 조회한다.
     * @author skhan
     */
    public UserResponse.Info findUserInfo(String userId){
        return UserResponse.Info.of(findByUserId(userId));
    }

    /**
     * @api /api/user/v1/existsByRsdntRgnmb
     * @description 주민등록번호로 이미 등록된 사용자인지 체크한다.
     * @throws GlobalException
     * @author skhan
     */
    public void existsByRsdntRgnmb(String rsdntRgnmb){
        if(!userRepository.existsByRsdntRgnmb(rsdntRgnmb)) {
            throw new GlobalException(ErrorCode.USER_EXIST);
        }
    }
}

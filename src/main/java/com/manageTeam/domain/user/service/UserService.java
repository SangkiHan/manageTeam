package com.manageTeam.domain.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.user.dto.UserDto;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.global.util.AESUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	
	/**
	 * @api /api/user/v1/save
	 * @description 회원가입
	 * @throws GlobalException
	 * @author skhan
	 */
	public void save(UserDto.Save request) throws Exception{
		//이미 등록되어 있는 사용자인지 체크한다.
		if(!userRepository.existsByRsdntRgnmb(AESUtil.encrypt(request.getRsdntRgnmb()))) {
			throw new GlobalException(ErrorCode.USER_EXIST);
		}
		
		Team team = teamRepository.findById(request.getTeam_id())
				.orElseThrow(() -> new GlobalException(ErrorCode.TEAM_UNKNOWN));
		User user = new User(request);
		user.setTeam(team);
		userRepository.save(user);
	}
	
	/**
	 * @api /api/user/v1/findUserInfo
	 * @description 사용자를 상세 조회한다.
	 * @author skhan
	 */
	public UserDto.Info findUserInfo(String userId){
		return userRepository.findUserInfo(userId)
				.orElseThrow(() -> new GlobalException(ErrorCode.USER_UNKNOWN));
	}
	
	/**
	 * @api /api/user/v1/existsByRsdntRgnmb
	 * @description 주민등록번호로 이미 등록된 사용자인지 체크한다.
	 * @throws GlobalException
	 * @author skhan
	 */
	public void existsByRsdntRgnmb(String rsdntRgnmb){
		if(!userRepository.existsByRsdntRgnmb(AESUtil.encrypt(rsdntRgnmb))) {
			throw new GlobalException(ErrorCode.USER_EXIST);
		}
	}
}

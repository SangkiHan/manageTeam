package com.manageTeam.domain.user.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.user.dto.UserDto;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.entity.Team;
import com.manageTeam.entity.User;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.util.AESUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	
	/**
	 * @api /api/user/v1/save
	 * @description 회원가입 및 사용자를 수정한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 */
	public void save(UserDto.Save request) throws Exception{
		Team team = teamRepository.findById(request.getTeam_id())
				.orElseThrow(() -> new GlobalException("USR0001","해당 팀 데이터가 없습니다. 관리자에게 문의 해주세요"));
		User user = new User(request);
		user.setTeam(team);
		userRepository.save(user);
	}
	
	/**
	 * @api /api/user/v1/findUserInfo
	 * @description 사용자를 상세 조회한다.
	 * @throws Exception
	 * @author skhan
	 */
	public UserDto.Info findUserInfo(String userId){
		return userRepository.findUserInfo(userId)
				.orElseThrow(() -> new GlobalException("USR0002","해당 사용자 데이터가 없습니다. 관리자에게 문의 해주세요"));
	}
	
	/**
	 * @api /api/user/v1/existsByRsdntRgnmb
	 * @description 주민등록번호로 이미 등록된 사용자인지 체크한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 */
	public void existsByRsdntRgnmb(String rsdntRgnmb){
		if(userRepository.existsByRsdntRgnmb(AESUtil.encrypt(rsdntRgnmb))) {
			new GlobalException("USR0003","이미 해당 주민번호로 등록된 팀원이 존재합니다.");
		}
	}
}

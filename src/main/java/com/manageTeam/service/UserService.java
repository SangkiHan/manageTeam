package com.manageTeam.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.manageTeam.dto.UserDto;
import com.manageTeam.entity.Team;
import com.manageTeam.entity.User;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.TeamRepository;
import com.manageTeam.repository.UserRepository;
import com.manageTeam.util.AESUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	
	public void save(UserDto.Save request) throws Exception{
		Team team = teamRepository.findById(request.getTeam_id())
				.orElseThrow(() -> new GlobalException("USR0001","해당 팀 데이터가 없습니다. 관리자에게 문의 해주세요"));
		User user = new User(request);
		user.setTeam(team);
		userRepository.save(user);
	}
	
	public UserDto.Info findUserInfo(String userId){
		return userRepository.findUserInfo(userId);
	}
	
	public void existsByRsdntRgnmb(String rsdntRgnmb){
		if(userRepository.existsByRsdntRgnmb(AESUtil.encrypt(rsdntRgnmb))) {
			new GlobalException("USR0002","이미 해당 주민번호로 등록된 팀원이 존재합니다.");
		}
	}
}

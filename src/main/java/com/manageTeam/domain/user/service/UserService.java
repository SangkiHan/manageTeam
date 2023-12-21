package com.manageTeam.domain.user.service;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.domain.team.service.TeamReadService;
import com.manageTeam.domain.user.dto.UserRequest;
import com.manageTeam.domain.user.dto.UserResponse;
import com.manageTeam.domain.user.entity.User;
import com.manageTeam.domain.user.repository.UserRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.global.util.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	
	private final UserRepository userRepository;
	private final UserReadService userReadService;
	private final TeamReadService teamReadService;
	
	/**
	 * @api /api/user/v1/save
	 * @description 회원가입
	 * @throws GlobalException
	 * @author skhan
	 */
	public UserResponse.Info save(UserRequest.Save request){
		//이미 등록되어 있는 사용자인지 체크한다.
		userReadService.existsByRsdntRgnmb(request.getRsdntRgnmb());
		
		Team team = teamReadService.findById(request.getTeam_id());

		User user = request.toEntity();
		user.changeTeam(team);

		return UserResponse.Info.of(userRepository.save(user));
	}

}

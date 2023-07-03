package com.manageTeam.service;

import org.springframework.stereotype.Service;

import com.manageTeam.dto.UserDto;
import com.manageTeam.entity.User;
import com.manageTeam.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public void save(UserDto.Save request) throws Exception{
		User user = new User(request);
		userRepository.save(user);
	}
}

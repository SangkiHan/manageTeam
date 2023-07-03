package com.manageTeam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.UserDto;
import com.manageTeam.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	
	/*
	 * 회원가입
	 * */
	@PostMapping("/save")
	public void save(@RequestBody UserDto.Save request) throws Exception {
		userService.save(request);
	}
	
	@GetMapping("findById")
	public UserDto.Info findUserInfo(String userId){
		return userService.findUserInfo(userId);
	}
}

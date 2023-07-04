package com.manageTeam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * 회원가입 및 수정
	 * */
	@PostMapping("/save")
	public void save(@RequestBody UserDto.Save request) throws Exception {
		userService.save(request);
	}
	
	/*
	 * 사용자 상세조회
	 * */
	@GetMapping("/findUserInfo")
	public UserDto.Info findUserInfo(String userId){
		return userService.findUserInfo(userId);
	}
	
	@PostMapping("/existsByRsdntRgnmb")
	public void existsByRsdntRgnmb(@RequestParam(value = "rsdntRgnmb") String rsdntRgnmb) {
		userService.existsByRsdntRgnmb(rsdntRgnmb);
	}
}

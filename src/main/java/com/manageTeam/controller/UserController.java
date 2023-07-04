package com.manageTeam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.UserDto;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	
	/**
	 * @description 회원가입 및 사용자를 수정한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	@PostMapping("/save")
	public void save(@RequestBody UserDto.Save request) throws Exception {
		userService.save(request);
	}
	
	/**
	 * @description 사용자를 상세 조회한다.
	 * @throws Exception
	 * @author skhan
	 * */
	@GetMapping("/findUserInfo")
	public UserDto.Info findUserInfo(String userId){
		return userService.findUserInfo(userId);
	}
	
	/**
	 * @description 주민등록번호로 이미 등록된 사용자인지 체크한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	@PostMapping("/existsByRsdntRgnmb")
	public void existsByRsdntRgnmb(@RequestParam(value = "rsdntRgnmb") String rsdntRgnmb) {
		userService.existsByRsdntRgnmb(rsdntRgnmb);
	}
}

package com.manageTeam.domain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.user.dto.UserDto;
import com.manageTeam.domain.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	
	@ApiOperation(value="회원가입 및 사용자를 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody UserDto.Save request) throws Exception {
		userService.save(request);
	}
	
	@ApiOperation(value="사용자를 상세 조회한다.")
	@GetMapping("/v1/findUserInfo")
	public UserDto.Info findUserInfo(String userId){
		return userService.findUserInfo(userId);
	}
	
	@ApiOperation(value="주민등록번호로 이미 등록된 사용자인지 체크한다.")
	@PostMapping("/v1/existsByRsdntRgnmb")
	public void existsByRsdntRgnmb(@RequestParam(value = "rsdntRgnmb") String rsdntRgnmb) {
		userService.existsByRsdntRgnmb(rsdntRgnmb);
	}
}

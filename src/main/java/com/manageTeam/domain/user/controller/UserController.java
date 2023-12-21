package com.manageTeam.domain.user.controller;

import com.manageTeam.domain.user.dto.UserRequest;
import com.manageTeam.domain.user.dto.UserResponse;
import com.manageTeam.domain.user.service.UserReadService;
import com.manageTeam.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api("사용자 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	private final UserReadService userReadService;
	
	@ApiOperation(value="회원가입")
	@PostMapping("/v1/save")
	public void save(@RequestBody UserRequest.Save request) throws Exception {
		userService.save(request);
	}
	
	@ApiOperation(value="사용자를 상세 조회한다.")
	@GetMapping("/v1/findUserInfo")
	public UserResponse.Info findUserInfo(String userId){
		return userReadService.findUserInfo(userId);
	}
	
	@ApiOperation(value="주민등록번호로 이미 등록된 사용자인지 체크한다.")
	@PostMapping("/v1/existsByRsdntRgnmb")
	public void existsByRsdntRgnmb(@RequestParam(value = "rsdntRgnmb") String rsdntRgnmb) {
		userReadService.existsByRsdntRgnmb(rsdntRgnmb);
	}
}

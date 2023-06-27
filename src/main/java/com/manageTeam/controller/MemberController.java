package com.manageTeam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.MemberDto;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	
	
	@PostMapping("/v1/save")
	public void save(@RequestBody MemberDto.Save request) {
	}
	
	@PostMapping("/v1/selectOne")
	public MemberDto.Info selectOne(@RequestBody MemberDto.Id request) {
		return new MemberDto.Info();
	}
	
	@PostMapping("/v1/selectList")
	public List<MemberDto.Info> selectList(@RequestBody MemberDto.Id request) {
		return new ArrayList<>();
	}

}

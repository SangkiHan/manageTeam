package com.manageTeam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.dto.MemberDto;
import com.manageTeam.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/v1/save")
	public void save(@RequestBody MemberDto.Save request) {
		memberService.save(request);
	}
	
	@GetMapping("/v1/findById")
	public MemberDto.Info findById(Long memberId) {
		return memberService.findById(memberId);
	}
	
	@GetMapping("/v1/selectList")
	public List<MemberDto.Info> selectList(@RequestBody MemberDto.Id request) {
		return new ArrayList<>();
	}

}

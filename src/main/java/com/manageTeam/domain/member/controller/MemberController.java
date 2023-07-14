package com.manageTeam.domain.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.member.dto.MemberConditionDto;
import com.manageTeam.domain.member.dto.MemberDto;
import com.manageTeam.domain.member.service.MemberService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@ApiOperation(value="팀원을 저장 및 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody MemberDto.Save request) {
		memberService.save(request);
	}
	
	@ApiOperation(value="팀원의 상태를 변경한다.")
	@PostMapping("/v1/status")
	public void status(@RequestBody MemberDto.Status request) {
		memberService.status(request);
	}
	
	@ApiOperation(value="팀원을 상세조회한다.")
	@GetMapping("/v1/findById")
	public MemberDto.Info findById(Long memberId) {
		return memberService.findById(memberId);
	}
	
	@ApiOperation(value="팀원의 목록을 조회한다.")
	@GetMapping("/v1/findAll")
	public Page<MemberDto.Info> selectList(MemberConditionDto conditionDto, Pageable pageable) {
		return memberService.findAllByCondition(conditionDto, pageable);
	}
	
	@ApiOperation(value="이미 등록된 주민번호 인지 체크한다.")
	@PostMapping("/v1/existsByRsdntRgnmb")
	public void existsByRsdntRgnmb(@RequestParam(value = "rsdntRgnmb") String rsdntRgnmb) {
		memberService.existsByRsdntRgnmb(rsdntRgnmb);
	}

}

package com.manageTeam.domain.member.controller;

import com.manageTeam.domain.member.dto.MemberRequest;
import com.manageTeam.domain.member.dto.MemberResponse;
import com.manageTeam.domain.member.service.MemberReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manageTeam.domain.member.dto.MemberConditionDto;
import com.manageTeam.domain.member.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api("팀원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
	
	private final MemberService memberService;
	private final MemberReadService memberReadService;
	
	@ApiOperation(value="팀원을 저장 및 수정한다.")
	@PostMapping("/v1/save")
	public void save(@RequestBody MemberRequest.Save request) {
		memberService.save(request);
	}
	
	@ApiOperation(value="팀원의 상태를 변경한다.")
	@PostMapping("/v1/status")
	public void status(@RequestBody MemberRequest.Status request) {
		memberService.status(request);
	}
	
	@ApiOperation(value="팀원을 상세조회한다.")
	@GetMapping("/v1/findById")
	public MemberResponse.Info findById(Long memberId) {
		return memberReadService.findOne(memberId);
	}
	
	@ApiOperation(value="팀원의 목록을 조회한다.")
	@GetMapping("/v1/findAll")
	public Page<MemberResponse.Info> selectList(MemberConditionDto conditionDto, Pageable pageable) {
		return memberReadService.findAllByCondition(conditionDto, pageable);
	}
	
	@ApiOperation(value="이미 등록된 주민번호 인지 체크한다.")
	@PostMapping("/v1/existsByRsdntRgnmb")
	public void existsByRsdntRgnmb(@RequestParam(value = "rsdntRgnmb") String rsdntRgnmb) {
		memberReadService.existsByRsdntRgnmb(rsdntRgnmb);
	}

}

package com.manageTeam.domain.member.service;

import javax.transaction.Transactional;

import com.manageTeam.domain.member.dto.MemberRequest;
import com.manageTeam.domain.member.dto.MemberResponse;
import com.manageTeam.domain.team.service.TeamReadService;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.member.repository.MemberRepository;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final MemberReadService memberReadService;
	private final TeamReadService teamReadService;
	
	
	/**
	 * @api /api/member/v1/save
	 * @description 팀원을 저장 및 수정한다.
	 * @author skhan
	 * */
	public MemberResponse.Save save(MemberRequest.Save request) {
		//주민번호 암호화하여 이미 등록된 팀원인지 체크
		memberReadService.existsByRsdntRgnmb(request.getRsdntRgnmb());
		Team team = teamReadService.findById(request.getTeamId());
		return MemberResponse.Save.of(memberRepository.save(request.toEntity(team)));
	}
	
	/**
	 * @api /api/member/v1/status
	 * @description 팀원의 상태를 변경한다.
	 * @author skhan
	 * */
	public void status(MemberRequest.Status request) {
		memberReadService.findById(request.getMemberId()).changeStatus(request.getActivateStatus());
	}


}

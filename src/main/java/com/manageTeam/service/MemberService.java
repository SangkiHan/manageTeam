package com.manageTeam.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.dto.MemberConditionDto;
import com.manageTeam.dto.MemberDto;
import com.manageTeam.entity.Member;
import com.manageTeam.entity.Team;
import com.manageTeam.exception.GlobalException;
import com.manageTeam.repository.MemberRepository;
import com.manageTeam.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	
	public void save(MemberDto.Save request) {
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("MEM0001","해당 팀원이 존재하지 않습니다. 관리자에게 문의해주세요."));
		Member member = new Member(request);
		member.changeTeam(team);
		
		memberRepository.save(member);
	}
	
	public void status(MemberDto.Status request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new GlobalException("MEM0002","해당 팀원이 존재하지 않습니다. 관리자에게 문의해주세요."));
		member.changeStatus(request.getActivateStatus());
	}
	
	public MemberDto.Info findById(Long memberId) {
		Member result = memberRepository.findById(memberId)
				.orElseThrow(() -> new GlobalException("MEM0003","해당 팀원이 존재하지 않습니다. 관리자에게 문의해주세요."));
		
		MemberDto.Info member = new MemberDto.Info(result);
		return member;
	}
	
	public Page<MemberDto.Info> findAllByCondition(MemberConditionDto conditionDto, Pageable pageable) {
		return memberRepository.findAllByCondition(conditionDto, pageable);
	}

}

package com.manageTeam.domain.member.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.member.dto.MemberConditionDto;
import com.manageTeam.domain.member.dto.MemberDto;
import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.member.repository.MemberRepository;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.global.util.AESUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	
	
	/**
	 * @api /api/member/v1/save
	 * @description 팀원을 저장 및 수정한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public void save(MemberDto.Save request) {
		//주민번호 암호화하여 이미 등록된 팀원인지 체크
		if(!memberRepository.existsByRsdntRgnmb(AESUtil.encrypt(request.getRsdntRgnmb()))) {
			throw new GlobalException(ErrorCode.USER_EXIST);
		}
		
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException(ErrorCode.TEAM_UNKNOWN));
		Member member = new Member(request);
		member.changeTeam(team);
		
		memberRepository.save(member);
	}
	
	/**
	 * @api /api/member/v1/status
	 * @description 팀원의 상태를 변경한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public void status(MemberDto.Status request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new GlobalException(ErrorCode.MEMBER_UNKNOWN));
		member.changeStatus(request.getActivateStatus());
	}
	
	/**
	 * @api /api/member/v1/findById
	 * @description 팀원을 상세조회한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public MemberDto.Info findById(Long memberId) {
		Member result = memberRepository.findById(memberId)
				.orElseThrow(() -> new GlobalException(ErrorCode.MEMBER_UNKNOWN));
		
		MemberDto.Info member = new MemberDto.Info(result);
		return member;
	}
	
	/**
	 * @api /api/member/v1/findAll
	 * @description 팀원의 목록을 조회한다.
	 * @author skhan
	 * */
	public Page<MemberDto.Info> findAllByCondition(MemberConditionDto conditionDto, Pageable pageable) {
		return memberRepository.findAllByCondition(conditionDto, pageable);
	}
	
	
	/**
	 * @api /api/member/v1/existsByRsdntRgnmb
	 * @description 이미 등록된 주민번호 인지 체크한다.
	 * @throws GlobalException
	 * @author skhan
	 * */
	public void existsByRsdntRgnmb(String rsdntRgnmb){
		if(!memberRepository.existsByRsdntRgnmb(AESUtil.encrypt(rsdntRgnmb))) {
			throw new GlobalException(ErrorCode.USER_EXIST);
		}
	}
}

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
import com.manageTeam.util.AESUtil;

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
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	public void save(MemberDto.Save request) {
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException("MEM0001","해당 팀원이 존재하지 않습니다. 관리자에게 문의해주세요."));
		Member member = new Member(request);
		member.changeTeam(team);
		
		memberRepository.save(member);
	}
	
	/**
	 * @api /api/member/v1/status
	 * @description 팀원의 상태를 변경한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	public void status(MemberDto.Status request) {
		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new GlobalException("MEM0002","해당 팀원이 존재하지 않습니다. 관리자에게 문의해주세요."));
		member.changeStatus(request.getActivateStatus());
	}
	
	/**
	 * @api /api/member/v1/findById
	 * @description 팀원을 상세조회한다.
	 * @throws GlobalException ,Exception
	 * @author skhan
	 * */
	public MemberDto.Info findById(Long memberId) {
		Member result = memberRepository.findById(memberId)
				.orElseThrow(() -> new GlobalException("MEM0003","해당 팀원이 존재하지 않습니다. 관리자에게 문의해주세요."));
		
		MemberDto.Info member = new MemberDto.Info(result);
		return member;
	}
	
	/**
	 * @api /api/member/v1/findAll
	 * @description 팀원의 목록을 조회한다.
	 * @throws Exception
	 * @author skhan
	 * */
	public Page<MemberDto.Info> findAllByCondition(MemberConditionDto conditionDto, Pageable pageable) {
		return memberRepository.findAllByCondition(conditionDto, pageable);
	}
	
	
	/**
	 * @api /api/member/v1/existsByRsdntRgnmb
	 * @description 이미 등록된 주민번호 인지 체크한다.
	 * @throws GlobalException, Exception
	 * @author skhan
	 * */
	public void existsByRsdntRgnmb(String rsdntRgnmb){
		if(memberRepository.existsByRsdntRgnmb(AESUtil.encrypt(rsdntRgnmb))) {
			new GlobalException("USR0002","이미 해당 주민번호로 등록된 팀원이 존재합니다.");
		}
	}
}

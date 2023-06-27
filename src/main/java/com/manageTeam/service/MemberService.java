package com.manageTeam.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
				.orElseThrow(() -> new GlobalException("MEM0002","해당 팀 데이터가 없음"));
		Member member = new Member(request);
		member.changeTeam(team);
		
		memberRepository.save(member);
	}
	
	public MemberDto.Info findById(Long memberId) {
		Member result = memberRepository.findById(memberId)
				.orElseThrow(() -> new GlobalException("MEM0001","해당 팀원 데이터가 없음"));
		
		MemberDto.Info member = new MemberDto.Info(result);
		return member;
	}
	
	public List<MemberDto.Info> findAll() {
		return new ArrayList<>();
	}

}

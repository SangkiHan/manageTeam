package com.manageTeam.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.global.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	
}

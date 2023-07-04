package com.manageTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
	
}

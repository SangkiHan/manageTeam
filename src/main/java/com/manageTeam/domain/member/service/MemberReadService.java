package com.manageTeam.domain.member.service;

import com.manageTeam.domain.member.dto.MemberConditionDto;
import com.manageTeam.domain.member.dto.MemberResponse;
import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.member.repository.MemberRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.global.util.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberReadService {

    private final MemberRepository memberRepository;

    public void existsByRsdntRgnmb(String rsdntRgnmb){
        if(!memberRepository.existsByRsdntRgnmb(AESUtil.encrypt(rsdntRgnmb))) {
            throw new GlobalException(ErrorCode.USER_EXIST);
        }
    }

    public Member findById(Long memberId){
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new GlobalException(ErrorCode.MEMBER_UNKNOWN));
    }

    /**
     * @api /api/member/v1/findAll
     * @description 팀원의 목록을 조회한다.
     * @author skhan
     * */
    public Page<MemberResponse.Info> findAllByCondition(MemberConditionDto conditionDto, Pageable pageable) {
        return memberRepository.findAllByCondition(conditionDto, pageable);
    }

    /**
     * @api /api/member/v1/findById
     * @description 팀원을 상세조회한다.
     * @throws GlobalException
     * @author skhan
     * */
    public MemberResponse.Info findOne(Long memberId) {
        return MemberResponse.Info.of(findById(memberId));
    }
}

package com.rptp.rptpSpringBoot.core.member.service;

import com.rptp.rptpSpringBoot.core.member.domain.Member;
import com.rptp.rptpSpringBoot.core.member.domain.MemberRepository;
import com.rptp.rptpSpringBoot.core.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(SignUpRequest req) {
        return  memberRepository.save(buildMember(req)).getMemberId();
    }

    private Member buildMember(SignUpRequest req) {
        return Member.builder()
                .name(req.getName())
                .password(req.getPassword())
                .profilePhoto(req.getProfilePhoto())
                .nickName(req.getNickName())
                .build();
    }

}

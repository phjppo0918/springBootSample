package com.rptp.rptpSpringBoot.service;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
import com.rptp.rptpSpringBoot.repository.impliment.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //화원가입
    public Long join(MemberDTO memberDTO) {
        // 같은 이름의 있는 중복 회원 x
        validateDuplicateMember(memberDTO);

        memberRepository.save(memberDTO);
        return memberDTO.getId();
    }

    private void validateDuplicateMember(MemberDTO memberDTO) {
        memberRepository.findByName(memberDTO.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    //전체 회원 조회
    public List<MemberDTO> findMembers() {
        return memberRepository.findAll();
    }

    // id에 맞는 회원 조회
    public Optional<MemberDTO> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

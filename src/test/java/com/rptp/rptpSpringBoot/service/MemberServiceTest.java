package com.rptp.rptpSpringBoot.service;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
import com.rptp.rptpSpringBoot.repository.impliment.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memoryMemberRepository;
    MemberService memberService;

    @BeforeEach
    public void 테스트_시작전_실행(){
       memoryMemberRepository = new MemoryMemberRepository();
       memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void 테스트_종료시_실행() {
        memoryMemberRepository.deleteAll();
    }


    @Test
    void 회원가입() {
        //given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("홍길동");
        //when
        Long saveId = memberService.join(memberDTO);

        //then
        MemberDTO findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(memberDTO.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 회원가입_시_중복회원가입_예외() {
        //given
        MemberDTO member1 = new MemberDTO();
        member1.setName("홍길동");

        MemberDTO member2 = new MemberDTO();
        member2.setName("홍길동");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
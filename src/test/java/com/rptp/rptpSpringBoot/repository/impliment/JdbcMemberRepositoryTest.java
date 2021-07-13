package com.rptp.rptpSpringBoot.repository.impliment;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
import com.rptp.rptpSpringBoot.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class JdbcMemberRepositoryTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        //Given
        MemberDTO member = new MemberDTO();
        member.setName("루핑이2");
        //When
        Long saveId = memberService.join(member);
        //Then
        MemberDTO findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        MemberDTO member1 = new MemberDTO();
        member1.setName("spring");
        MemberDTO member2 = new MemberDTO();
        member2.setName("spring");
        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}
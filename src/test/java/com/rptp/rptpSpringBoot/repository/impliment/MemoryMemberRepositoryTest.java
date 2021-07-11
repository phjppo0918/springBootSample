package com.rptp.rptpSpringBoot.repository.impliment;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    /*
    @AfterEach
    public void afterEach() {
        //중복 방지를 위하여 각 테스트 종료시 초기화 시켜줘야함

    }
    */


    @Test
    public void save() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("spring");

        repository.save(memberDTO);

        MemberDTO result = repository.findById(memberDTO.getId()).get();
       // Assertions.assertEquals(memberDTO, result);
        Assertions.assertThat(memberDTO).isEqualTo(result);
    }

    @Test
    public void findByName() {
        MemberDTO member1 = new MemberDTO();
        member1.setName("spring1");
        repository.save(member1);

        MemberDTO member2 = new MemberDTO();
        member2.setName("spring2");
        repository.save(member2);

        MemberDTO result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        MemberDTO member1 = new MemberDTO();
        member1.setName("spring1");
        repository.save(member1);

        MemberDTO member2 = new MemberDTO();
        member2.setName("spring2");
        repository.save(member2);

        List<MemberDTO> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}

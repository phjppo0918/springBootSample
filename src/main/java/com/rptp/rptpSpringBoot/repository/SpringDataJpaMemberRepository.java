package com.rptp.rptpSpringBoot.repository;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository을 extend받으면 자동으로 Spring 컨테이너에 Bean으로 알아서 입력됨.
public interface SpringDataJpaMemberRepository extends JpaRepository<MemberDTO, Long> , MemberRepository{

    @Override
    Optional<MemberDTO> findByName(String name);
}

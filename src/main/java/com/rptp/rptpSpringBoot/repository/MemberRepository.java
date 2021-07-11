package com.rptp.rptpSpringBoot.repository;

import com.rptp.rptpSpringBoot.model.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MemberDTO save(MemberDTO memberDTO);
    Optional<MemberDTO> findById(Long id);
    Optional<MemberDTO> findByName(String name);
    List<MemberDTO> findAll();
}

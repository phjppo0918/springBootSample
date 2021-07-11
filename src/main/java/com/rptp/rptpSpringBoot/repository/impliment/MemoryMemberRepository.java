package com.rptp.rptpSpringBoot.repository.impliment;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberDTO> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        memberDTO.setId(++sequence);
        store.put(memberDTO.getId(),memberDTO);
        return memberDTO;
    }

    @Override
    public Optional<MemberDTO> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<MemberDTO> findByName(String name) {
        return store.values().stream()
                .filter(memberDTO -> memberDTO.getName().equals(name))
                .findAny();
    }

    @Override
    public List<MemberDTO> findAll() {
        return new ArrayList<>(store.values());
    }
}

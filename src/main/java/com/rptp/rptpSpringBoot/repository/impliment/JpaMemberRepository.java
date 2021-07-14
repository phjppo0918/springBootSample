package com.rptp.rptpSpringBoot.repository.impliment;

import com.rptp.rptpSpringBoot.model.MemberDTO;
import com.rptp.rptpSpringBoot.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

//@Repository
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        em.persist(memberDTO);
        return memberDTO;
    }

    @Override
    public Optional<MemberDTO> findById(Long id) {
        MemberDTO memberDTO = em.find(MemberDTO.class, id);
        return Optional.ofNullable(memberDTO);
    }

    @Override
    public Optional<MemberDTO> findByName(String name) {
        List<MemberDTO> result = em.createQuery("select m from member m where m.name = :name", MemberDTO.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<MemberDTO> findAll() {
        return em.createQuery("select m from member m", MemberDTO.class)
                .getResultList();
    }
}

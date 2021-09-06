package com.rptp.rptpSpringBoot.core.vet.service;

import com.rptp.rptpSpringBoot.common.exceptions.ResourceNotFoundException;
import com.rptp.rptpSpringBoot.core.vet.domain.Vet;
import com.rptp.rptpSpringBoot.core.vet.domain.VetRepository;
import com.rptp.rptpSpringBoot.core.vet.dto.VetRequest;
import com.rptp.rptpSpringBoot.core.vet.dto.VetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetService {
    private final VetRepository vetRepository;

    @Transactional
    public Long saveVet(VetRequest req) {
        return vetRepository.save(
                Vet.builder()
                        .name(req.getName())
                        .email(req.getEmail())
                        .build()
        ).getVetId();
    }

    @Transactional(readOnly = true)
    public List<VetResponse> findAll() {
        return VetResponse.listOf(vetRepository.findAll());
    }

    @Transactional(readOnly = true)
    public VetResponse findVet(Long vetId) {
        return VetResponse.of(findById(vetId));
    }

    private Vet findById(Long vetId) {
        return vetRepository.findById(vetId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vet","id",vetId));
    }
}

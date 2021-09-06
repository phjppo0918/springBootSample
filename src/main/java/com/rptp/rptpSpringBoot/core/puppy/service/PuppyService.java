package com.rptp.rptpSpringBoot.core.puppy.service;

import com.rptp.rptpSpringBoot.common.exceptions.ResourceNotFoundException;
import com.rptp.rptpSpringBoot.core.puppy.domain.Puppy;
import com.rptp.rptpSpringBoot.core.puppy.domain.PuppyRepository;
import com.rptp.rptpSpringBoot.core.puppy.dto.PuppyRequest;
import com.rptp.rptpSpringBoot.core.puppy.dto.PuppyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuppyService {
    private final PuppyRepository puppyRepository;

    @Transactional
    public Long savePuppy(PuppyRequest req) {
        return puppyRepository.save(
          Puppy.builder()
                  .name(req.getName())
                  .age(req.getAge())
                  .breed(req.getBreed())
                  .build()
        ).getPuppyId();
    }

    @Transactional(readOnly = true)
    public List<PuppyResponse> findAll() {
        return PuppyResponse.listOf(puppyRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PuppyResponse findPuppy(Long puppyId) {
        return PuppyResponse.of(findById(puppyId));
    }

    private Puppy findById(Long puppyId) {
        return puppyRepository.findById(puppyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Puppy","id",puppyId));
    }
 }

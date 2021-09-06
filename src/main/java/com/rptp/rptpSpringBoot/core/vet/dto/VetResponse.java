package com.rptp.rptpSpringBoot.core.vet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rptp.rptpSpringBoot.core.puppy.dto.PuppyResponse;
import com.rptp.rptpSpringBoot.core.vet.domain.Vet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VetResponse {

    private Long vetId;

    private String name;

    private String email;

    private List<PuppyResponse> puppyResponses;

    public static VetResponse of(Vet vet){

        return new VetResponse(
                vet.getVetId(),
                vet.getName(),
                vet.getEmail(),
                PuppyResponse.listOf(vet.getPuppyList())
        );
    }

    public static List<VetResponse> listOf(List<Vet> vets) {
        return vets.stream()
                .map(VetResponse::of)
                .collect(Collectors.toList());
    }
}

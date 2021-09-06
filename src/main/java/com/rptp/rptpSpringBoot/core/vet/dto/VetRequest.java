package com.rptp.rptpSpringBoot.core.vet.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VetRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}

package com.rptp.rptpSpringBoot.core.puppy.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PuppyRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Integer age;

    @NotBlank
    private String breed;
}

package com.rptp.rptpSpringBoot.core.member.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    private String profilePhoto;

    private String nickName;
}

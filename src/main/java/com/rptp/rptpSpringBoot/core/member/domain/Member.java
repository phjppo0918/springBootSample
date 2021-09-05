package com.rptp.rptpSpringBoot.core.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column
    private String password;

    private String imageUrl;

    private String email;

    private AuthProvider provider;

    private String providerId;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    @Builder
    public Member(String name, String password, String imageUrl, String email, AuthProvider provider, String providerId, String nickName) {
        this.name = name;
        this.password = password;
        this.imageUrl = imageUrl;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.nickName = nickName;
    }

    public Member update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}

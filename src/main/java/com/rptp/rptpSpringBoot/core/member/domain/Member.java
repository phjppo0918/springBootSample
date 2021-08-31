package com.rptp.rptpSpringBoot.core.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false)
    private String password;

    private String profilePhoto;

    @Column(nullable = false)
    private String nickName;

    @Builder
    public Member(String name, String password, String profilePhoto, String nickName) {
        this.name = name;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.nickName = nickName;
    }
}

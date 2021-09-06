package com.rptp.rptpSpringBoot.core.vet.domain;

import com.rptp.rptpSpringBoot.core.puppy.domain.Puppy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetId;

    @Column(nullable = false)
    private String name;

    private String email;

    @OneToMany(mappedBy = "vet", cascade = CascadeType.MERGE)
    private List<Puppy> puppyList = new ArrayList<>();

    @Builder
    public Vet(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

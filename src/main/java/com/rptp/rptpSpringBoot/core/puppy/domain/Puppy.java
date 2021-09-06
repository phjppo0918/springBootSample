package com.rptp.rptpSpringBoot.core.puppy.domain;

import com.rptp.rptpSpringBoot.core.vet.domain.Vet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Puppy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long puppyId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String breed;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Builder
    public Puppy(String name, Integer age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }
}

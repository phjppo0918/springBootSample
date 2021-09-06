package com.rptp.rptpSpringBoot.core.puppy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rptp.rptpSpringBoot.core.puppy.domain.Puppy;
import com.rptp.rptpSpringBoot.core.vet.domain.Vet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PuppyResponse {

    private Long puppyId;

    private String name;

    private Integer age;

    private String breed;

    private Long vetId;

    private String vetName;

    public PuppyResponse(Long puppyId, String name, Integer age, String breed) {
        this.puppyId = puppyId;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public static PuppyResponse of(Puppy puppy) {
        Optional<Vet> vet = Optional.ofNullable(puppy.getVet());
        if(vet.isPresent()) {
            return new PuppyResponse(
                    puppy.getPuppyId(),
                    puppy.getName(),
                    puppy.getAge(),
                    puppy.getBreed(),
                    vet.get().getVetId(),
                    vet.get().getName()
            );
        }else {
            return new PuppyResponse(
                    puppy.getPuppyId(),
                    puppy.getName(),
                    puppy.getAge(),
                    puppy.getBreed()
            );
        }
    }

    public static List<PuppyResponse> listOf(List<Puppy> puppies) {
        return puppies.stream()
                .map(PuppyResponse::of)
                .collect(Collectors.toList());
    }
}

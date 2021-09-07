package com.rptp.rptpSpringBoot.controller.api;

import com.rptp.rptpSpringBoot.core.vet.dto.VetResponse;
import com.rptp.rptpSpringBoot.core.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vet")
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping("")
    public ResponseEntity<List<VetResponse>> getVets() {
        return ResponseEntity.ok(vetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetResponse> getVet(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vetService.findVet(id));
    }
}

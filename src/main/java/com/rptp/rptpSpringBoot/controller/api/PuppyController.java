package com.rptp.rptpSpringBoot.controller.api;

import com.rptp.rptpSpringBoot.core.puppy.dto.PuppyResponse;
import com.rptp.rptpSpringBoot.core.puppy.service.PuppyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/puppy")
@RequiredArgsConstructor
public class PuppyController {

    private final PuppyService puppyService;

    @GetMapping("")
    public ResponseEntity<List<PuppyResponse>> getPuppies() {
        return ResponseEntity.ok(puppyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuppyResponse> getPuppy(@PathVariable("id") Long id) {
        return ResponseEntity.ok(puppyService.findPuppy(id));
    }
}

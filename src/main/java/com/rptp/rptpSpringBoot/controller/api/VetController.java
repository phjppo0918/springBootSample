package com.rptp.rptpSpringBoot.controller.api;

import com.rptp.rptpSpringBoot.core.vet.dto.VetRequest;
import com.rptp.rptpSpringBoot.core.vet.dto.VetResponse;
import com.rptp.rptpSpringBoot.core.vet.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping("")
    public ResponseEntity<Void> registerPuppy(@Valid @RequestBody VetRequest vetRequest){
        Long result = vetService.saveVet(vetRequest);
        return ResponseEntity.created(URI.create("/api/vet/"+result)).build();
    }
}

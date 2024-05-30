package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.pedagogicelement.NewPedagogicElementRequest;
import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementResponse;
import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementUpdateRequest;
import com.ensah.proctorsync.services.pedagogicelement.IPedagogicElementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pedagogic-element")
@RequiredArgsConstructor
public class PedagogicElementController {
    private final IPedagogicElementService pedagogicElementService;


    @GetMapping
    public ResponseEntity<Collection<PedagogicElementResponse>> getAllPedagogicElements() {
        return ResponseEntity.ok(pedagogicElementService.getAllPedagogicElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedagogicElementResponse> getPedagogicElementById(@PathVariable UUID id) {
        return ResponseEntity.ok(pedagogicElementService.getPedagogicElementById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPedagogicElement(@RequestBody NewPedagogicElementRequest newPedagogicElementRequest) {
        return ResponseEntity.ok(pedagogicElementService.save(newPedagogicElementRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePedagogicElement(
            @PathVariable UUID id,
            @RequestBody @Valid PedagogicElementUpdateRequest pedagogicElementUpdateRequest
    ) {
        return ResponseEntity.ok(pedagogicElementService.update(id, pedagogicElementUpdateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePedagogicElement(@PathVariable UUID id) {
        return ResponseEntity.ok(pedagogicElementService.delete(id));
    }

}

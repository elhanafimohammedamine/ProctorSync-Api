package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.administrator.AdministratorResponse;
import com.ensah.proctorsync.DTOs.administrator.AdministratorUpdateRequest;
import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;
import com.ensah.proctorsync.services.administrator.IAdministratorService;
import io.swagger.v3.oas.models.media.UUIDSchema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/administrator")
@RequiredArgsConstructor
public class AdministratorController {

    private final IAdministratorService administratorService;

    @GetMapping
    public ResponseEntity<Collection<AdministratorResponse>> getAllAdministrators() {
        return ResponseEntity.ok(administratorService.getAllAdministrators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorResponse> getAdministratorById(@PathVariable UUID id) {
        return ResponseEntity.ok(administratorService.getAdministratorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdministrator(@RequestBody NewAdministratorRequest newAdministratorRequest) {
        return ResponseEntity.ok(administratorService.insert(newAdministratorRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAdministrator(
            @PathVariable UUID id,
            @RequestBody @Valid AdministratorUpdateRequest administratorUpdateRequest
            )
    {
        return ResponseEntity.ok(administratorService.update(id, administratorUpdateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdministrator(@PathVariable UUID id) {
        return ResponseEntity.ok(administratorService.delete(id));
    }
}

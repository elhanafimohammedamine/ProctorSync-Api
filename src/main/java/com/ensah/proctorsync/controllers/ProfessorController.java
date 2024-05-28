package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.professor.NewProfessorRequest;
import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import com.ensah.proctorsync.DTOs.professor.ProfessorUpdateRequest;
import com.ensah.proctorsync.services.professor.IProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorController {
    private final IProfessorService professorService;

    @GetMapping
    public ResponseEntity<Collection<ProfessorResponse>> getAllProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> getProfessorById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewProfessor(@RequestBody NewProfessorRequest newProfessorRequest) {
        return ResponseEntity.ok(professorService.save(newProfessorRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProfessor(
            @PathVariable UUID id,
            @Valid @RequestBody ProfessorUpdateRequest professorUpdateRequest
    ) {
        return ResponseEntity.ok(professorService.update(id, professorUpdateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable UUID id) {
        return ResponseEntity.ok(professorService.delete(id));
    }
}

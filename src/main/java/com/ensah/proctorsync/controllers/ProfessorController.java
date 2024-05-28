package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.professor.NewProfessorRequest;
import com.ensah.proctorsync.services.professor.IProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/professor")
@RequiredArgsConstructor
public class ProfessorController {
    private final IProfessorService professorService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewProfessor(@RequestBody NewProfessorRequest newProfessorRequest) {
        return ResponseEntity.ok(professorService.save(newProfessorRequest));
    }

}

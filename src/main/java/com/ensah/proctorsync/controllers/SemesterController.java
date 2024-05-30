package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.semester.SemesterResponse;
import com.ensah.proctorsync.services.semester.ISemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/semester")
@RequiredArgsConstructor
public class SemesterController {
    private final ISemesterService semesterService;



    @GetMapping
    public ResponseEntity<Collection<SemesterResponse>> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }
}

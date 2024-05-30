package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.examtype.ExamTypeResponse;
import com.ensah.proctorsync.services.examtype.IExamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/exam-type")
@RequiredArgsConstructor
public class ExamTypeController {
    private final IExamTypeService examTypeService;


    @GetMapping
    public ResponseEntity<Collection<ExamTypeResponse>> getAllExamTypes() {
        return ResponseEntity.ok(examTypeService.getAllExamTypes());
    }


}

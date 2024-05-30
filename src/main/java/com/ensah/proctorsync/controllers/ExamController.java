package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.exam.NewExamRequest;
import com.ensah.proctorsync.services.exam.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exam")
@RequiredArgsConstructor
public class ExamController {
    private final IExamService examService;


    @PostMapping("/create")
    ResponseEntity<String> createExam(@RequestBody NewExamRequest newExamRequest) {
        return ResponseEntity.ok(examService.create(newExamRequest));
    }
}

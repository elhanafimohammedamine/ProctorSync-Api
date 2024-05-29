package com.ensah.proctorsync.controllers;


import com.ensah.proctorsync.DTOs.department.DepartmentResponse;
import com.ensah.proctorsync.services.department.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Collection<DepartmentResponse>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}

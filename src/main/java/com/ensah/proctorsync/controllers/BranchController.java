package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.branch.BranchResponse;
import com.ensah.proctorsync.services.branch.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {

    private final IBranchService branchService;

    @GetMapping
    public ResponseEntity<Collection<BranchResponse>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

}

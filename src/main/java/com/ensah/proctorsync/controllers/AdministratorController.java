package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;
import com.ensah.proctorsync.services.administrator.IAdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/administrator")
@RequiredArgsConstructor
public class AdministratorController {

    private IAdministratorService administratorService;

    @PostMapping("/create")
    public ResponseEntity<String> createAdministrator(NewAdministratorRequest newAdministratorRequest) {
        return ResponseEntity.ok(administratorService.insert(newAdministratorRequest));
    }
}

package com.ensah.proctorsync.controllers;


import com.ensah.proctorsync.DTOs.authentication.AuthenticationRequest;
import com.ensah.proctorsync.DTOs.authentication.AuthenticationResponse;
import com.ensah.proctorsync.DTOs.authentication.RegistrationRequest;
import com.ensah.proctorsync.services.authentication.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok(authenticationService.register(registrationRequest));
    }




}

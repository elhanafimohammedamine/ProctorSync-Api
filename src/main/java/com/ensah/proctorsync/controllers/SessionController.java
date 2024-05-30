package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.session.SessionResponse;
import com.ensah.proctorsync.services.session.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {
    private final ISessionService sessionService;

    @GetMapping
    public ResponseEntity<Collection<SessionResponse>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

}

package com.ensah.proctorsync.services.session;

import com.ensah.proctorsync.DTOs.session.SessionResponse;
import com.ensah.proctorsync.entities.Session;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ISessionService {
    Collection<SessionResponse> getAllSessions();
    Optional<Session> getSessionById(UUID id);
}

package com.ensah.proctorsync.services.session;

import com.ensah.proctorsync.DTOs.session.SessionResponse;
import com.ensah.proctorsync.entities.Session;
import com.ensah.proctorsync.mappers.ISessionMapper;
import com.ensah.proctorsync.repositories.session.ISessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {
    private final ISessionRepository sessionRepository;
    private final ISessionMapper sessionMapper;


    @Override
    public Collection<SessionResponse> getAllSessions() {
        return sessionMapper.sessionsToSessionsResponse(
                sessionRepository.findAll()
        );
    }

    @Override
    public Optional<Session> getSessionById(UUID id) {
        return sessionRepository.findById(id);
    }
}

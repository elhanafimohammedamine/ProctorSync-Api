package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.session.SessionResponse;
import com.ensah.proctorsync.entities.Session;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ISessionMapper {

    SessionResponse sessionToSessionResponse(Session session);
    Collection<SessionResponse> sessionsToSessionsResponse(Collection<Session> sessions);
}

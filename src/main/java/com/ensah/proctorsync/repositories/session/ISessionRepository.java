package com.ensah.proctorsync.repositories.session;

import com.ensah.proctorsync.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISessionRepository extends JpaRepository<Session, UUID> {
}

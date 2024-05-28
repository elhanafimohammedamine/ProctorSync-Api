package com.ensah.proctorsync.repositories.professor;

import com.ensah.proctorsync.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByEmail(String email);
}

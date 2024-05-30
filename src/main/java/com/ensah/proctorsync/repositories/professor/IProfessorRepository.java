package com.ensah.proctorsync.repositories.professor;

import com.ensah.proctorsync.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Struct;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByEmail(String email);
    boolean existsByEmail(String email);
    Collection<Professor> findAllByGroupIsNull();
}

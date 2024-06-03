package com.ensah.proctorsync.repositories.professor;

import com.ensah.proctorsync.entities.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByEmail(String email);
    boolean existsByEmail(String email);
    Collection<Professor> findAllByGroupIsNull();
<<<<<<< Updated upstream
    Collection<Professor> findAllByDeletedAtIsNullOrderByCreatedAt();
=======

    @Query("SELECT p FROM Professor p " +
            "JOIN p.group g " +
            "WHERE g.id = :groupId " +
            "AND NOT EXISTS (" +
            "    SELECT m FROM Monitoring m " +
            "    JOIN m.exam e " +
            "    WHERE p MEMBER OF m.professors " +
            "    AND e.startDateTime < :newExamEndDateTime " +
            "    AND e.endDateTime > :newExamStartDateTime" +
            ")")
    Page<Professor> findAvailableProfessorsInGroupWithLimit(@Param("groupId") UUID groupId, @Param("newExamStartDateTime") LocalDateTime newExamStartDateTime, @Param("newExamEndDateTime") LocalDateTime newExamEndDateTime, Pageable pageable);


>>>>>>> Stashed changes
}

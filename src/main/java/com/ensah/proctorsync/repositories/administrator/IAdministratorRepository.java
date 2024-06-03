package com.ensah.proctorsync.repositories.administrator;

import com.ensah.proctorsync.DTOs.administrator.AdministratorResponse;
import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import com.ensah.proctorsync.entities.Administrator;
import com.ensah.proctorsync.entities.Professor;
<<<<<<< Updated upstream
=======
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

<<<<<<< Updated upstream
=======
import java.time.LocalDateTime;
>>>>>>> Stashed changes
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IAdministratorRepository extends JpaRepository<Administrator, UUID> {
    Optional<Administrator> findByEmail(String email);
    boolean existsByEmail(String email);
<<<<<<< Updated upstream
    Collection<Administrator> findAllByDeletedAtIsNullOrderByCreatedAt();
=======

    @Query("SELECT a FROM Administrator a " +
            "WHERE NOT EXISTS (" +
            "    SELECT m FROM Monitoring m " +
            "    JOIN m.exam e " +
            "    WHERE m.administrator = a " +
            "    AND e.startDateTime < :newExamEndDateTime " +
            "    AND e.endDateTime > :newExamStartDateTime" +
            ")")
    Optional<Administrator> findAvailableAdministrator(@Param("newExamStartDateTime") LocalDateTime newExamStartDateTime, @Param("newExamEndDateTime") LocalDateTime newExamEndDateTime);
>>>>>>> Stashed changes
}

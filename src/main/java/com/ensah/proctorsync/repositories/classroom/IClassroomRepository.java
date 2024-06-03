package com.ensah.proctorsync.repositories.classroom;

import com.ensah.proctorsync.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClassroomRepository extends JpaRepository<Classroom, UUID> {
    Optional<Classroom> findClassroomByNameAndBloc(String name, String bloc);
    Collection<Classroom> findAllByDeletedAtIsNullOrderByCreatedAtDesc();
    @Query("SELECT r FROM Classroom r WHERE r.deletedAt IS NULL AND " +
            "(r.id NOT IN " +
            "(SELECT m.classroom.id FROM Monitoring m " +
            "WHERE (m.exam.startDateTime < :endDateTime AND m.exam.endDateTime > :startDateTime)) " +
            "OR NOT EXISTS (SELECT 1 FROM Monitoring m WHERE m.classroom.id = r.id))")
    Collection<Classroom> findAvailableRooms(@Param("startDateTime") LocalDateTime startDateTime,
                                  @Param("endDateTime") LocalDateTime endDateTime);
}

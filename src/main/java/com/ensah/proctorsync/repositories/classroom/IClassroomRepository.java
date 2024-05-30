package com.ensah.proctorsync.repositories.classroom;

import com.ensah.proctorsync.DTOs.classroom.ClassRoomResponse;
import com.ensah.proctorsync.entities.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IClassroomRepository extends JpaRepository<Classroom, UUID> {
    Optional<Classroom> findClassroomByNameAndBloc(String name, String bloc);

    @Query("SELECT c FROM Classroom c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) AND c.deletedAt IS NULL ORDER BY c.createdAt")
    Page<Classroom> getAllClassrooms(String query, Pageable pageable);
}

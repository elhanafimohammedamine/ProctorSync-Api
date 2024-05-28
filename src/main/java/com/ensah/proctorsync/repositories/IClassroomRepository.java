package com.ensah.proctorsync.repositories;

import com.ensah.proctorsync.entities.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IClassroomRepository extends JpaRepository<Classroom, UUID> {
    Optional<Classroom> findClassroomByNameAndBloc(String name, String bloc);
    Page<Classroom> findClassroomsByNameContainingIgnoreCase(String query, Pageable pageable);
}

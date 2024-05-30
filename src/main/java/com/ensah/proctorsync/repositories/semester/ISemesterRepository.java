package com.ensah.proctorsync.repositories.semester;

import com.ensah.proctorsync.entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISemesterRepository extends JpaRepository<Semester, UUID> {
    Optional<Semester> findByName(String name);
}

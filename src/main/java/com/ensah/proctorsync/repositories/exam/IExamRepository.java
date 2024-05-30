package com.ensah.proctorsync.repositories.exam;

import com.ensah.proctorsync.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IExamRepository extends JpaRepository<Exam, UUID> {

}

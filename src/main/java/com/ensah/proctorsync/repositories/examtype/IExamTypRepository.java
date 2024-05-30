package com.ensah.proctorsync.repositories.examtype;

import com.ensah.proctorsync.entities.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IExamTypRepository extends JpaRepository<ExamType, UUID> {
}

package com.ensah.proctorsync.services.examtype;

import com.ensah.proctorsync.DTOs.examtype.ExamTypeResponse;
import com.ensah.proctorsync.entities.ExamType;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IExamTypeService {
    Collection<ExamTypeResponse> getAllExamTypes();
    Optional<ExamType> getExamTypeById(UUID id);
}

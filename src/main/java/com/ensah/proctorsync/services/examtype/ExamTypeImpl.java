package com.ensah.proctorsync.services.examtype;

import com.ensah.proctorsync.DTOs.examtype.ExamTypeResponse;
import com.ensah.proctorsync.entities.ExamType;
import com.ensah.proctorsync.mappers.IExamTypeMapper;
import com.ensah.proctorsync.repositories.examtype.IExamTypRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamTypeImpl implements IExamTypeService {
    private final IExamTypeMapper examTypeMapper;
    private final IExamTypRepository examTypRepository;
    @Override
    public Collection<ExamTypeResponse> getAllExamTypes() {
        return examTypeMapper.examTypesToExamTypesResponse(
                examTypRepository.findAll()
        );
    }

    @Override
    public Optional<ExamType> getExamTypeById(UUID id) {
        return examTypRepository.findById(id);
    }
}

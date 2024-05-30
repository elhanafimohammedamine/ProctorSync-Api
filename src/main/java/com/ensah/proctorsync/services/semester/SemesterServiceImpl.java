package com.ensah.proctorsync.services.semester;

import com.ensah.proctorsync.DTOs.semester.SemesterResponse;
import com.ensah.proctorsync.entities.Semester;
import com.ensah.proctorsync.mappers.ISemesterMapper;
import com.ensah.proctorsync.repositories.semester.ISemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements ISemesterService {

    private final ISemesterRepository semesterRepository;
    private final ISemesterMapper semesterMapper;
    @Override
    public Collection<SemesterResponse> getAllSemesters() {
        return semesterMapper.semestersToSemestersResponse(
                semesterRepository.findAll()
        );
    }

    @Override
    public Optional<Semester> getSemesterByName(String name) {
        return semesterRepository.findByName(name);
    }
}

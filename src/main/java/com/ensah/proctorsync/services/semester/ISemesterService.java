package com.ensah.proctorsync.services.semester;

import com.ensah.proctorsync.DTOs.semester.SemesterResponse;
import com.ensah.proctorsync.entities.Semester;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ISemesterService {
    Collection<SemesterResponse> getAllSemesters();
    Optional<Semester> getSemesterByName(String name);
}

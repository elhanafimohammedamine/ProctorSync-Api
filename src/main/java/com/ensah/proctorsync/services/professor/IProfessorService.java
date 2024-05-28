package com.ensah.proctorsync.services.professor;

import com.ensah.proctorsync.DTOs.professor.NewProfessorRequest;
import com.ensah.proctorsync.entities.Professor;

import java.util.Optional;

public interface IProfessorService {
    String save(NewProfessorRequest newProfessorRequest);


}

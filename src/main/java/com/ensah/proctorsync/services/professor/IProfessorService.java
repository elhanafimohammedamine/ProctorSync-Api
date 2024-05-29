package com.ensah.proctorsync.services.professor;

import com.ensah.proctorsync.DTOs.professor.NewProfessorRequest;
import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import com.ensah.proctorsync.DTOs.professor.ProfessorUpdateRequest;
import com.ensah.proctorsync.entities.Professor;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IProfessorService {
    String save(NewProfessorRequest newProfessorRequest);
    Collection<ProfessorResponse> getAllProfessors();
    ProfessorResponse getProfessorById(UUID professorId);
    String update(UUID professorId, ProfessorUpdateRequest professorUpdateRequest);
    String delete(UUID professorId);
    Collection<Professor> getProfessorsById(Collection<UUID> professorIds);


}

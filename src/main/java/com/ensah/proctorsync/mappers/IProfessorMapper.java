package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import com.ensah.proctorsync.entities.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IProfessorMapper {
    Professor professorResponseToProfessor(ProfessorResponse professorResponse);
    Collection<Professor> professorsResponseToProfessors(Collection<ProfessorResponse> professorsResponse);
    @Mapping(target = "id", source = "id")
    ProfessorResponse professorToProfessorResponse(Professor professor);
    Collection<ProfessorResponse> professorsToProfessorsResponse(Collection<Professor> professor);


}

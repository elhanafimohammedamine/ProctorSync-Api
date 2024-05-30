package com.ensah.proctorsync.mappers;


import com.ensah.proctorsync.DTOs.semester.SemesterResponse;
import com.ensah.proctorsync.entities.Semester;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ISemesterMapper {
   SemesterResponse semesterToSemesterResponse(Semester semester);
   Collection<SemesterResponse> semestersToSemestersResponse(Collection<Semester> semesters);
}

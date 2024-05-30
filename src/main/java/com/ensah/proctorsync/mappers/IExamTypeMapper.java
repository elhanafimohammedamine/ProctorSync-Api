package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.examtype.ExamTypeResponse;
import com.ensah.proctorsync.entities.ExamType;
import io.swagger.v3.oas.models.examples.Example;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IExamTypeMapper {
    @Mapping(target = "id", source = "id")
    ExamTypeResponse examTypeToExamTypeResponse(ExamType examType);
    Collection<ExamTypeResponse> examTypesToExamTypesResponse(Collection<ExamType> examTypes);
}

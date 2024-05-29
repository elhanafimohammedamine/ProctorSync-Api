package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.department.DepartmentResponse;
import com.ensah.proctorsync.entities.Department;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IDepartmentMapper {
    DepartmentResponse departmentToDepartmentResponse(Department department);
    Collection<DepartmentResponse> departmentsToDepartmentResponses(Collection<Department> departments);
}

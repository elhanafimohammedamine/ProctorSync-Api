package com.ensah.proctorsync.services.department;

import com.ensah.proctorsync.DTOs.department.DepartmentResponse;
import com.ensah.proctorsync.entities.Department;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IDepartmentService {
    Optional<Department> getDepartmentById(UUID departmentId);
    Collection<DepartmentResponse> getAllDepartments();

}

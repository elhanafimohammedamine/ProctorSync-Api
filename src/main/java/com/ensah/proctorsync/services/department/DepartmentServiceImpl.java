package com.ensah.proctorsync.services.department;


import com.ensah.proctorsync.DTOs.department.DepartmentResponse;
import com.ensah.proctorsync.entities.Department;
import com.ensah.proctorsync.mappers.IDepartmentMapper;
import com.ensah.proctorsync.repositories.department.IDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService{

    private final IDepartmentRepository departmentRepository;
    private final IDepartmentMapper departmentMapper;

    @Override
    public Optional<Department> getDepartmentById(UUID departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public Collection<DepartmentResponse> getAllDepartments() {
        return departmentMapper
                .departmentsToDepartmentResponses(
                        departmentRepository.findAll()
                );
    }
}

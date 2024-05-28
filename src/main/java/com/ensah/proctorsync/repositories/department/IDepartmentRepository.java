package com.ensah.proctorsync.repositories.department;

import com.ensah.proctorsync.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDepartmentRepository extends JpaRepository<Department, UUID> {
}

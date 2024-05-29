package com.ensah.proctorsync.repositories.department;

import com.ensah.proctorsync.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, UUID> {
}

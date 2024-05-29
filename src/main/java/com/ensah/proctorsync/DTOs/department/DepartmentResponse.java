package com.ensah.proctorsync.DTOs.department;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DepartmentResponse {
    private UUID id;
    private String departmentName;
}

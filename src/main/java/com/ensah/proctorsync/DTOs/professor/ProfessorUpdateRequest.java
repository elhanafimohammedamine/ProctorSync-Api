package com.ensah.proctorsync.DTOs.professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorUpdateRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UUID branchId;
    private UUID departmentId;
}


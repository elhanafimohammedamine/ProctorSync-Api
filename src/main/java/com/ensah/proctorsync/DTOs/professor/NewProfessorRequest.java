package com.ensah.proctorsync.DTOs.professor;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProfessorRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UUID branchId;
    private UUID departmentId;
}

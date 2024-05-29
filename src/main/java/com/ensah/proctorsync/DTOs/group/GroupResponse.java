package com.ensah.proctorsync.DTOs.group;

import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
public class GroupResponse {
    private UUID id;
    private String groupName;
    private String description;
    private LocalDateTime createdAt;
    private Collection<ProfessorResponse> professors;


}

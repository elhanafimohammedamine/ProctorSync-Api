package com.ensah.proctorsync.DTOs.pedagogicelement;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PedagogicElementUpdateRequest {
    private UUID id;
    private String elementTitle;
    private UUID levelId;
    private UUID elementTypeId;
    private UUID professorId;
    private UUID coordinatorId;
}

package com.ensah.proctorsync.DTOs.pedagogicelement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewPedagogicElementRequest {
    private String elementTitle;
    private UUID levelId;
    private UUID elementTypeId;
    private UUID professorId;
    private UUID coordinatorId;
}

package com.ensah.proctorsync.DTOs.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupUpdateRequest {
    private UUID id;
    private String groupName;
    private String description;
}


package com.ensah.proctorsync.DTOs.semester;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Data
@Builder
public class SemesterResponse {
    private UUID id;
    private String name;
}

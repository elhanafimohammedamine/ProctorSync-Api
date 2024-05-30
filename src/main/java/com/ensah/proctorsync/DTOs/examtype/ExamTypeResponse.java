package com.ensah.proctorsync.DTOs.examtype;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ExamTypeResponse {
    private UUID id;
    private String name;

}

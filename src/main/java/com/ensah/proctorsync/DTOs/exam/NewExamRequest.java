package com.ensah.proctorsync.DTOs.exam;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
public class NewExamRequest {

    private String academicYear;
    private int actualDuration;
    private int plannedDuration;
    private UUID subjectId;
    private UUID examTypeId;
    private UUID sessionId;
    private String examDate;
    private String startTime;
    private Collection<UUID> roomIds;
}

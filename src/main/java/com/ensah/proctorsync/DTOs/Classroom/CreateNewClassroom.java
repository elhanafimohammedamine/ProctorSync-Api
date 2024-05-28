package com.ensah.proctorsync.DTOs.Classroom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewClassroom {
    private String roomName;
    private int capacity;
    private String bloc;
}

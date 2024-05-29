package com.ensah.proctorsync.DTOs.classroom;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClassRoomResponse {
    private UUID id;
    private String name;
    private String bloc;
    private int capacity;

}

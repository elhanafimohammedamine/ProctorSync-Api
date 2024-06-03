package com.ensah.proctorsync.DTOs.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfos {
    private UUID roomId;
    private int monitorsCount;
    private UUID groupId;
}

package com.ensah.proctorsync.DTOs.level;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LevelResponse {
    private UUID id;
    private String levelTitle;
}

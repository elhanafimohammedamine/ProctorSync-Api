package com.ensah.proctorsync.DTOs.elementype;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ElementTypeResponse {
    private UUID id;
    private String typeTitle;
}

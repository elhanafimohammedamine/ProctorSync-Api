package com.ensah.proctorsync.DTOs.session;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SessionResponse {

    private UUID id;
    private String name;

}

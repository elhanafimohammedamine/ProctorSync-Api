package com.ensah.proctorsync.DTOs.branch;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BranchResponse {
    private UUID id;
    private String branchName;

}

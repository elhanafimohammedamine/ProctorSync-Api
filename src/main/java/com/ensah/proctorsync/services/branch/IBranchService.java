package com.ensah.proctorsync.services.branch;

import com.ensah.proctorsync.DTOs.branch.BranchResponse;
import com.ensah.proctorsync.entities.Branch;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IBranchService {

    Optional<Branch> getBranchById(UUID branchId);
    Collection<BranchResponse> getAllBranches();
}

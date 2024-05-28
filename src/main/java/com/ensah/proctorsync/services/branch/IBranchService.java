package com.ensah.proctorsync.services.branch;

import com.ensah.proctorsync.entities.Branch;

import java.util.Optional;
import java.util.UUID;

public interface IBranchService {

    Optional<Branch> getBranchById(UUID branchId);
}

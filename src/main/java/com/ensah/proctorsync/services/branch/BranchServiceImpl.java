package com.ensah.proctorsync.services.branch;

import com.ensah.proctorsync.entities.Branch;
import com.ensah.proctorsync.repositories.branch.IBranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements IBranchService {

    private final IBranchRepository branchRepository;

    @Override
    public Optional<Branch> getBranchById(UUID branchId) {
        return branchRepository.findById(branchId);
    }
}

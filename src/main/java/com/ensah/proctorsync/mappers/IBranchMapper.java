package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.branch.BranchResponse;
import com.ensah.proctorsync.entities.Branch;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IBranchMapper {
    BranchResponse branchToBranchResponse(Branch branch);
    Collection<BranchResponse> branchesToBranchResponses(Collection<Branch> branches);


}

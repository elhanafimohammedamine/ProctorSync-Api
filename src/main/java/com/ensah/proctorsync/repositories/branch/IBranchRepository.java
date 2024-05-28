package com.ensah.proctorsync.repositories.branch;

import com.ensah.proctorsync.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IBranchRepository extends JpaRepository<Branch, UUID> {

}

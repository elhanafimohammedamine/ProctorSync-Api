package com.ensah.proctorsync.repositories.branch;

import com.ensah.proctorsync.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IBranchRepository extends JpaRepository<Branch, UUID> {

}

package com.ensah.proctorsync.repositories.group;

import com.ensah.proctorsync.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IGroupRepository extends JpaRepository<Group, UUID> {
    boolean existsByGroupName(String name);
}

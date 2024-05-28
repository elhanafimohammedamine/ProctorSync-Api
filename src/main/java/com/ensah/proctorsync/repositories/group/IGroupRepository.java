package com.ensah.proctorsync.repositories.group;

import com.ensah.proctorsync.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IGroupRepository extends JpaRepository<Group, UUID> {
    boolean existsByGroupName(String name);
}

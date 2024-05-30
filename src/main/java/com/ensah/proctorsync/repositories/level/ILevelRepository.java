package com.ensah.proctorsync.repositories.level;

import com.ensah.proctorsync.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ILevelRepository extends JpaRepository<Level, UUID> {
}

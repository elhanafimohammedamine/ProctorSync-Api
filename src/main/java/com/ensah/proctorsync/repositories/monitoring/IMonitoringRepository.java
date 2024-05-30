package com.ensah.proctorsync.repositories.monitoring;

import com.ensah.proctorsync.entities.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMonitoringRepository extends JpaRepository<Monitoring, UUID> {
}

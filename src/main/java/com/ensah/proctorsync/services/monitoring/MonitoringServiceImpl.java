package com.ensah.proctorsync.services.monitoring;

import com.ensah.proctorsync.entities.Monitoring;
import com.ensah.proctorsync.repositories.monitoring.IMonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements IMonitoringService {
    private final IMonitoringRepository monitoringRepository;

    @Override
    public Monitoring save(Monitoring monitoring) {
        return monitoringRepository.save(monitoring);
    }
}

package com.ensah.proctorsync.services.administrator;

import com.ensah.proctorsync.DTOs.administrator.AdministratorResponse;
import com.ensah.proctorsync.DTOs.administrator.AdministratorUpdateRequest;
import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;
import com.ensah.proctorsync.entities.Administrator;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IAdministratorService {

    Collection<AdministratorResponse> getAllAdministrators();
    AdministratorResponse getAdministratorById(UUID administratorId);
    String insert(NewAdministratorRequest newAdministratorRequest);
    String update(UUID administratorId, AdministratorUpdateRequest administratorUpdateRequest);
    String delete(UUID administratorId);
    Optional<Administrator> findAvailableAdministrator(LocalDateTime newExamStartDateTime, LocalDateTime newExamEndDateTime);


}

package com.ensah.proctorsync.services.administrator;

import com.ensah.proctorsync.DTOs.administrator.AdministratorResponse;
import com.ensah.proctorsync.DTOs.administrator.AdministratorUpdateRequest;
import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;

import java.util.Collection;
import java.util.UUID;

public interface IAdministratorService {

    Collection<AdministratorResponse> getAllAdministrators();
    AdministratorResponse getAdministratorById(UUID administratorId);
    String insert(NewAdministratorRequest newAdministratorRequest);
    String update(UUID administratorId, AdministratorUpdateRequest administratorUpdateRequest);
    String delete(UUID administratorId);

}

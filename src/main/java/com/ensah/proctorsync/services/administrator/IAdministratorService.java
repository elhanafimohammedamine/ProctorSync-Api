package com.ensah.proctorsync.services.administrator;

import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;

public interface IAdministratorService {

    String insert(NewAdministratorRequest newAdministratorRequest);
}

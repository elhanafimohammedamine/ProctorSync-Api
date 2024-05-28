package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.administrator.AdministratorResponse;
import com.ensah.proctorsync.entities.Administrator;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IAdministratorMapper {
    Administrator administratorResponseToAdministrator(AdministratorResponse administratorResponse);
    Collection<Administrator> administratorsResponseToAdministrators(Collection<AdministratorResponse> administratorsResponse);

    AdministratorResponse administratorToAdministratorResponse(Administrator administrator);
    Collection<AdministratorResponse> administratorsToAdministratorResponses(Collection<Administrator> administrators);

}

package com.ensah.proctorsync.services.administrator;

import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;
import com.ensah.proctorsync.entities.Administrator;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.repositories.administrator.IAdministratorRepository;
import com.ensah.proctorsync.services.professor.ProfessorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements IAdministratorService {
    private final IAdministratorRepository administratorRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @Override
    public String insert(NewAdministratorRequest newAdministratorRequest) {
        // check whether the professor already exists
        Administrator administrator = administratorRepository
                .findByEmail(newAdministratorRequest.getEmail())
                .orElseThrow(
                        () -> {
                            AlreadyExistException alreadyExistException = new AlreadyExistException("Administrator with email " + newAdministratorRequest.getEmail() + " already exists");
                            LOGGER.error("Error while saving administrator, exception thrown because of administrator already exists", alreadyExistException);
                            return alreadyExistException;
                        }
                );


        Administrator newAdministrator = Administrator
                .builder()
                .firstName(newAdministratorRequest.getFirstName())
                .lastName(newAdministratorRequest.getLastName())
                .email(newAdministratorRequest.getEmail())
                .phone(newAdministratorRequest.getPhone())
                .build();


        Administrator savedAdministrator =  administratorRepository.save(newAdministrator);
        // Check if the professor was successfully saved by verifying the ID
        boolean professorSavedWithSuccess = savedAdministrator.getId() != null;

        if (professorSavedWithSuccess) {
            LOGGER.info("Administrator saved successfully: {}", savedAdministrator);
        } else {
            LOGGER.error("Failed to save administrator: {}" ,newAdministrator);
        }

        return "Administrator created successfully";
    }
}

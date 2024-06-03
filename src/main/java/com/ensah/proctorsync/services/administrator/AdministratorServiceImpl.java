package com.ensah.proctorsync.services.administrator;

import com.ensah.proctorsync.DTOs.administrator.AdministratorResponse;
import com.ensah.proctorsync.DTOs.administrator.AdministratorUpdateRequest;
import com.ensah.proctorsync.DTOs.administrator.NewAdministratorRequest;
import com.ensah.proctorsync.entities.Administrator;
import com.ensah.proctorsync.entities.Professor;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.exception.ApiRequestException;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.helpers.OperationCheck;
import com.ensah.proctorsync.mappers.IAdministratorMapper;
import com.ensah.proctorsync.repositories.administrator.IAdministratorRepository;
import com.ensah.proctorsync.services.professor.ProfessorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements IAdministratorService {
    private final IAdministratorRepository administratorRepository;
    private final IAdministratorMapper administratorMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @Override
    public Collection<AdministratorResponse> getAllAdministrators() {
        return administratorMapper
                .administratorsToAdministratorResponses(
                        administratorRepository.findAllByDeletedAtIsNullOrderByCreatedAt()
                );
    }

    @Override
    public AdministratorResponse getAdministratorById(UUID administratorId) {
        Administrator administrator = administratorRepository
                .findById(administratorId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Administrator with id " + administratorId + " does not exist");
                            LOGGER.error("Error while getting administrator, exception thrown because of not found administrator id = {}", administratorId,  notFoundException);
                            return notFoundException;
                        }
                );

        return administratorMapper.administratorToAdministratorResponse(administrator);
    }

    @Override
    public String insert(NewAdministratorRequest newAdministratorRequest) {
        // check whether the professor already exists
       if(administratorExists(newAdministratorRequest.getEmail())) {
           AlreadyExistException alreadyExistException = new AlreadyExistException("Administrator with email " + newAdministratorRequest.getEmail() + " already exists");
           LOGGER.error("Error while saving administrator, exception thrown because of administrator already exists", alreadyExistException);
           throw  alreadyExistException;
       }


        Administrator newAdministrator = Administrator
                .builder()
                .firstName(newAdministratorRequest.getFirstName())
                .lastName(newAdministratorRequest.getLastName())
                .email(newAdministratorRequest.getEmail())
                .phone(newAdministratorRequest.getPhone())
                .createdAt(LocalDateTime.now())
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

    @Override
    public String update(UUID administratorId, AdministratorUpdateRequest administratorUpdateRequest) {

        // check ids match
        if (!administratorId.equals(administratorUpdateRequest.getId())) {
            ApiRequestException apiRequestException = new ApiRequestException("Id of professor does not match the id in the request body");
            LOGGER.error("Error while updating administrator, exception thrown because of administrator id {} does not match the one in the request body {} ", administratorId, administratorUpdateRequest.getId(), apiRequestException);
            throw  apiRequestException;
        }

        Administrator administrator = administratorRepository
                .findById(administratorId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Administrator with id " + administratorUpdateRequest.getId() + " does not exist");
                            LOGGER.error("Error while updating administrator, exception thrown because of administrator does not exist", notFoundException);
                            return notFoundException;
                        }
                );


        administrator.setFirstName(administratorUpdateRequest.getFirstName());
        administrator.setLastName(administratorUpdateRequest.getLastName());
        administrator.setEmail(administratorUpdateRequest.getEmail());
        administrator.setPhone(administratorUpdateRequest.getPhone());
        administrator.setUpdatedAt(LocalDateTime.now());

        Administrator updatedAdministrator = administratorRepository.save(administrator);
        return OperationCheck.check(updatedAdministrator, "Administrator updated successfully", "Failed to update administrator");
    }

    @Override
    public String delete(UUID administratorId) {
        Administrator administrator = administratorRepository
                .findById(administratorId)
                .orElseThrow(
                        () -> {
                            NotFoundException alreadyExistException = new NotFoundException("Administrator with id " + administratorId + " does not exist");
                            LOGGER.error("Error while deleting administrator, exception thrown because of administrator does not exist", alreadyExistException);
                            return alreadyExistException;
                        }
                );

        administrator.setDeletedAt(LocalDateTime.now());
        Administrator deletedAdministrator = administratorRepository.save(administrator);

        return OperationCheck.check(deletedAdministrator, "Administrator has been deleted successfully", "Failed to delete administrator");
    }

    @Override
    public Optional<Administrator> findAvailableAdministrator(LocalDateTime newExamStartDateTime, LocalDateTime newExamEndDateTime) {
        List<Administrator> administrators = administratorRepository.findAvailableAdministrator(newExamStartDateTime, newExamEndDateTime, PageRequest.of(0, 1)).getContent();
        return administrators.isEmpty() ? Optional.empty() : Optional.of(administrators.get(0));
    }

    private boolean administratorExists(String email) {
        return administratorRepository.existsByEmail(email);
    }

}

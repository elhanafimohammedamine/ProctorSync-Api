package com.ensah.proctorsync.services.professor;

import com.ensah.proctorsync.DTOs.professor.NewProfessorRequest;
import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import com.ensah.proctorsync.DTOs.professor.ProfessorUpdateRequest;
import com.ensah.proctorsync.entities.Branch;
import com.ensah.proctorsync.entities.Department;
import com.ensah.proctorsync.entities.Professor;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.exception.ApiException;
import com.ensah.proctorsync.exception.ApiRequestException;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.helpers.OperationCheck;
import com.ensah.proctorsync.mappers.IProfessorMapper;
import com.ensah.proctorsync.repositories.professor.IProfessorRepository;
import com.ensah.proctorsync.services.branch.IBranchService;
import com.ensah.proctorsync.services.department.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;



@Service
@Transactional
@RequiredArgsConstructor
public class ProfessorServiceImpl implements IProfessorService {

    private final IProfessorRepository professorRepository;
    private final IDepartmentService departmentService;
    private final IBranchService branchService;
    private final IProfessorMapper professorMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @Override
    public String save(NewProfessorRequest newProfessorRequest) {

        // check whether the professor already exists
        if(professorExists(newProfessorRequest.getEmail())) {
            AlreadyExistException alreadyExistException = new AlreadyExistException("Professor with email " + newProfessorRequest.getEmail() + " already exists");
            LOGGER.error("Error while saving professor, exception thrown because of professor already exists", alreadyExistException);
            throw  alreadyExistException;
        }


        // check whether the branch exists
        Branch branch = branchService
                .getBranchById(newProfessorRequest.getBranchId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Branch with id " + newProfessorRequest.getBranchId() + " does not exist");
                            LOGGER.error("Error while saving professor, exception thrown because of not found branch id = {}", newProfessorRequest.getBranchId(), notFoundException);
                            return notFoundException;
                        }
                );

        // check whether the department exists
        Department department = departmentService
                .getDepartmentById(newProfessorRequest.getDepartmentId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with id " + newProfessorRequest.getDepartmentId() + " does not exist");
                            LOGGER.error("Error while saving professor, exception thrown because of not found department id = {}", newProfessorRequest.getDepartmentId(), notFoundException);
                            return notFoundException;

                        }
                );


        // build new professor object
        Professor newProfessor = Professor
                .builder()
                .firstName(newProfessorRequest.getFirstName())
                .lastName(newProfessorRequest.getLastName())
                .email(newProfessorRequest.getEmail())
                .phone(newProfessorRequest.getPhone())
                .branch(branch)
                .department(department)
                .createdAt(LocalDateTime.now())
                .build();


        Professor savedProfessor =  professorRepository.save(newProfessor);
        return OperationCheck.check(savedProfessor, "Professor saved successfully", "Failed to save professor");
    }

    @Override
    public Collection<ProfessorResponse> getAllProfessors() {

        return professorMapper
                .professorsToProfessorsResponse(
                        professorRepository.findAll()
                );
    }

    @Override
    public ProfessorResponse getProfessorById(UUID professorId) {

        Professor professor = professorRepository
                .findById(professorId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Professor with id " + professorId + " does not exist");
                            LOGGER.error("Error while getting professor, exception thrown because of not found professor id = {}", professorId,  notFoundException);
                            return notFoundException;
                        }
                );

        return professorMapper.professorToProfessorResponse(professor);

    }

    @Override
    public String update(UUID professorId, ProfessorUpdateRequest professorUpdateRequest) {

        // check ids match
        if (professorId != professorUpdateRequest.getId()) {
            ApiRequestException apiRequestException = new ApiRequestException("Id of professor does not match the id in the request body");
            LOGGER.error("Error while updating professor, exception thrown because of professor id {} does not match the one in the request body {} ", professorId, professorUpdateRequest.getId(), apiRequestException);
            throw  apiRequestException;
        }

        // get existing professor
        Professor professor = professorRepository
                .findById(professorId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Professor with id " + professorUpdateRequest.getId() + " does not exist");
                            LOGGER.error("Error while updating professor, exception thrown because of professor does not exist", notFoundException);
                            return notFoundException;
                        }
                );


        // check whether the branch exists
        Branch branch = branchService
                .getBranchById(professorUpdateRequest.getBranchId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Branch with id " + professorUpdateRequest.getBranchId() + " does not exist");
                            LOGGER.error("Error while updating professor, exception thrown because of not found branch id = {}", professorUpdateRequest.getBranchId(), notFoundException);
                            return notFoundException;
                        }
                );

        // check whether the department exists
        Department department = departmentService
                .getDepartmentById(professorUpdateRequest.getDepartmentId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Department with id " + professorUpdateRequest.getDepartmentId() + " does not exist");
                            LOGGER.error("Error while updating professor, exception thrown because of not found department id = {}", professorUpdateRequest.getDepartmentId(), notFoundException);
                            return notFoundException;

                        }
                );


        professor.setFirstName(professorUpdateRequest.getFirstName());
        professor.setLastName(professorUpdateRequest.getLastName());
        professor.setEmail(professorUpdateRequest.getEmail());
        professor.setPhone(professorUpdateRequest.getPhone());
        professor.setBranch(branch);
        professor.setDepartment(department);
        professor.setUpdatedAt(LocalDateTime.now());


        Professor updatedProfessor = professorRepository.save(professor);

        return OperationCheck.check(updatedProfessor, "Professor updated successfully", "Failed to update professor");
    }

    @Override
    public String delete(UUID professorId) {
        Professor professor = professorRepository
                .findById(professorId)
                .orElseThrow(
                        () -> {
                            NotFoundException alreadyExistException = new NotFoundException("Professor with id " + professorId + " does not exist");
                            LOGGER.error("Error while deleting professor, exception thrown because of professor does not exist", alreadyExistException);
                            return alreadyExistException;
                        }
                );

        professor.setDeletedAt(LocalDateTime.now());
        Professor deletedProfessor =  professorRepository.save(professor);
        return OperationCheck.check(deletedProfessor, "Professor has been deleted successfully", "Failed to delete professor");


    }

    @Override
    public Collection<Professor> getProfessorsById(Collection<UUID> professorIds) {
        return professorRepository.findAllById(professorIds);
    }

    @Override
    public Optional<Professor> findProfessorById(UUID id) {
        return professorRepository.findById(id);
    }

    @Override
    public Collection<ProfessorResponse> getAllProfessorsWithoutGroup() {
        return professorMapper.professorsToProfessorsResponse(
                professorRepository.findAllByGroupIsNull()
        );
    }


    private boolean professorExists(String email) {
        return professorRepository.existsByEmail(email);
    }


}

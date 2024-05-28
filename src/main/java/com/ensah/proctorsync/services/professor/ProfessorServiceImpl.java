package com.ensah.proctorsync.services.professor;

import com.ensah.proctorsync.DTOs.professor.NewProfessorRequest;
import com.ensah.proctorsync.entities.Branch;
import com.ensah.proctorsync.entities.Department;
import com.ensah.proctorsync.entities.Professor;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.repositories.professor.IProfessorRepository;
import com.ensah.proctorsync.services.branch.IBranchService;
import com.ensah.proctorsync.services.department.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProfessorServiceImpl implements IProfessorService {

    private final IProfessorRepository professorRepository;
    private final IDepartmentService departmentService;
    private final IBranchService branchService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @Override
    public String save(NewProfessorRequest newProfessorRequest) {

        // check whether the professor already exists
        Professor professor = professorRepository
                .findByEmail(newProfessorRequest.getEmail())
                .orElseThrow(
                        () -> {
                            AlreadyExistException alreadyExistException = new AlreadyExistException("Professor with email " + newProfessorRequest.getEmail() + " already exists");
                            LOGGER.error("Error while saving professor, exception thrown because of professor already exists", alreadyExistException);
                            return alreadyExistException;
                        }
                );

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
                .build();


        Professor savedProfessor =  professorRepository.save(newProfessor);
        // Check if the professor was successfully saved by verifying the ID
        boolean professorSavedWithSuccess = savedProfessor.getId() != null;

        if (professorSavedWithSuccess) {
            LOGGER.info("Professor saved successfully: {}", savedProfessor);
        } else {
            LOGGER.error("Failed to save professor: {}", newProfessor);
        }

        return "Professor created successfully";
    }


}

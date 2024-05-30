package com.ensah.proctorsync.services.pedagogicelement;

import com.ensah.proctorsync.DTOs.pedagogicelement.NewPedagogicElementRequest;
import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementResponse;
import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementUpdateRequest;
import com.ensah.proctorsync.entities.ElementType;
import com.ensah.proctorsync.entities.Level;
import com.ensah.proctorsync.entities.PedagogicElement;
import com.ensah.proctorsync.entities.Professor;
import com.ensah.proctorsync.exception.ApiRequestException;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.helpers.OperationCheck;
import com.ensah.proctorsync.mappers.IPedagogicElementMapper;
import com.ensah.proctorsync.repositories.pedagogicelement.IPedagogicElementRepository;
import com.ensah.proctorsync.services.elementype.IElementTypeService;
import com.ensah.proctorsync.services.level.ILevelService;
import com.ensah.proctorsync.services.professor.IProfessorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PedagogicElementServiceImpl implements IPedagogicElementService {
    private final IPedagogicElementRepository pedagogicElementRepository;
    private final ILevelService levelService;
    private final IElementTypeService elementTypeService;
    private final IProfessorService professorService;
    private final IPedagogicElementMapper pedagogicElementMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(PedagogicElementServiceImpl.class);

    @Override
    public String save(NewPedagogicElementRequest newPedagogicElementRequest) {

        // check whether the level exists
        Level level = levelService
                .findByLevelById(newPedagogicElementRequest.getLevelId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Level with id " + newPedagogicElementRequest.getLevelId() + " not found!");
                            LOGGER.error("Error while saving pedagogic element, Level with id {} does not exist", newPedagogicElementRequest.getLevelId(), notFoundException);
                            return notFoundException;
                        }
                );


        // check whether the element type exists
        ElementType elementType = elementTypeService
                .findElementById(newPedagogicElementRequest.getElementTypeId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Element type with id " + newPedagogicElementRequest.getElementTypeId() + " not found!");
                            LOGGER.error("Error while saving pedagogic element, Element type with id {} does not exist", newPedagogicElementRequest.getElementTypeId(), notFoundException);
                            return notFoundException;
                        }
                );

        Professor professor = professorService
                .findProfessorById(newPedagogicElementRequest.getProfessorId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Professor with id " + newPedagogicElementRequest.getElementTypeId() + " not found!");
                            LOGGER.error("Error while saving pedagogic element, professor with id {} does not exist", newPedagogicElementRequest.getElementTypeId(), notFoundException);
                            return notFoundException;
                        }
                );


        Professor coordinator = professorService
                .findProfessorById(newPedagogicElementRequest.getCoordinatorId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Coordinator with id " + newPedagogicElementRequest.getElementTypeId() + " not found!");
                            LOGGER.error("Error while saving pedagogic element, coordinator with id {} does not exist", newPedagogicElementRequest.getElementTypeId(), notFoundException);
                            return notFoundException;
                        }
                );


        PedagogicElement newPedagogicElement = PedagogicElement
                .builder()
                .elementTitle(newPedagogicElementRequest.getElementTitle())
                .elementType(elementType)
                .level(level)
                .professor(professor)
                .coordinator(coordinator)
                .createdAt(LocalDateTime.now())
                .build();


        PedagogicElement savedPedagogicElement = pedagogicElementRepository.save(newPedagogicElement);

        return OperationCheck.check(savedPedagogicElement, "Pedagogic element saved successfully", "Failed to save new pedagogic element");
    }

    @Override
    public String update(UUID pedagogicElementId, PedagogicElementUpdateRequest pedagogicElementUpdateRequest) {

        System.out.println("=============================== element title : " + pedagogicElementUpdateRequest.getElementTitle());
        // check ids matching
        if (!pedagogicElementId.equals(pedagogicElementUpdateRequest.getId())) {
            ApiRequestException apiRequestException = new ApiRequestException("Id of Pedagogic element  does not match the id in the request body");
            LOGGER.error("Error while updating Pedagogic element, exception thrown because of Pedagogic element id {} does not match the one in the request body {} ", pedagogicElementId, pedagogicElementUpdateRequest.getId(), apiRequestException);
            throw  apiRequestException;
        }

        // check whether the level exists
        Level level = levelService
                .findByLevelById(pedagogicElementUpdateRequest.getLevelId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Level with id " + pedagogicElementUpdateRequest.getLevelId() + " not found!");
                            LOGGER.error("Error while updating pedagogic element, Level with id {} does not exist", pedagogicElementUpdateRequest.getLevelId(), notFoundException);
                            return notFoundException;
                        }
                );


        // check whether the element type exists
        ElementType elementType = elementTypeService
                .findElementById(pedagogicElementUpdateRequest.getElementTypeId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Element type with id " + pedagogicElementUpdateRequest.getElementTypeId() + " not found!");
                            LOGGER.error("Error while updating pedagogic element, Element type with id {} does not exist", pedagogicElementUpdateRequest.getElementTypeId(), notFoundException);
                            return notFoundException;
                        }
                );

        Professor professor = professorService
                .findProfessorById(pedagogicElementUpdateRequest.getProfessorId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Professor with id " + pedagogicElementUpdateRequest.getElementTypeId() + " not found!");
                            LOGGER.error("Error while updating pedagogic element, professor with id {} does not exist", pedagogicElementUpdateRequest.getElementTypeId(), notFoundException);
                            return notFoundException;
                        }
                );


        Professor coordinator = professorService
                .findProfessorById(pedagogicElementUpdateRequest.getCoordinatorId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Coordinator with id " + pedagogicElementId + " not found!");
                            LOGGER.error("Error while updating pedagogic element, coordinator with id {} does not exist", pedagogicElementId, notFoundException);
                            return notFoundException;
                        }
                );


        PedagogicElement pedagogicElement = pedagogicElementRepository
                .findById(pedagogicElementId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Pedagogic element with id " + pedagogicElementId + " not found!");
                            LOGGER.error("Error while updating pedagogic element, Pedagogic element with id {} does not exist", pedagogicElementId, notFoundException);
                            return notFoundException;
                        }
                );


        pedagogicElement.setElementTitle(pedagogicElementUpdateRequest.getElementTitle());
        pedagogicElement.setLevel(level);
        pedagogicElement.setElementType(elementType);
        pedagogicElement.setProfessor(professor);
        pedagogicElement.setCoordinator(coordinator);
        pedagogicElement.setUpdatedAt(LocalDateTime.now());

        PedagogicElement updatedPedagogicElement = pedagogicElementRepository.save(pedagogicElement);

        return OperationCheck.check(updatedPedagogicElement, "Pedagogic element updated successfully", "Failed to update pedagogic element");
    }

    @Override
    public String delete(UUID pedagogicElementId) {
        PedagogicElement pedagogicElement = pedagogicElementRepository
                .findById(pedagogicElementId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Pedagogic element with id " + pedagogicElementId + " not found!");
                            LOGGER.error("Error while updating pedagogic element, Pedagogic element with id {} does not exist", pedagogicElementId, notFoundException);
                            return notFoundException;
                        }
                );

        pedagogicElement.setDeletedAt(LocalDateTime.now());
        PedagogicElement deletedPedagogicElement = pedagogicElementRepository.save(pedagogicElement);

        return OperationCheck.check(deletedPedagogicElement, "Pedagogic element deleted successfully", "Failed to delete pedagogic element");
    }

    @Override
    public PedagogicElementResponse getPedagogicElementById(UUID pedagogicElementId) {

        PedagogicElement pedagogicElement = pedagogicElementRepository
                .findById(pedagogicElementId)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Pedagogic element with id " + pedagogicElementId + " not found!");
                            LOGGER.error("Error while getting pedagogic element, Pedagogic element with id {} does not exist", pedagogicElementId, notFoundException);
                            return notFoundException;
                        }
                );

        return pedagogicElementMapper.pedagogicElementToPedagogicElementResponse(pedagogicElement);
    }

    @Override
    public Collection<PedagogicElementResponse> getAllPedagogicElements() {
        return pedagogicElementMapper.pedagogicElementsToPedagogicElementsResponse(
                pedagogicElementRepository.findAll()
        );
    }


}

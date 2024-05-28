package com.ensah.proctorsync.services.Classroom;

import com.ensah.proctorsync.DTOs.classroom.NewClassroomRequest;
import com.ensah.proctorsync.entities.Classroom;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.repositories.classroom.IClassroomRepository;
import com.ensah.proctorsync.services.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements IClassroomService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final IClassroomRepository classroomRepository;

    @Override
    public Collection<Classroom> GetAllClassrooms(String searchQuery, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        if (searchQuery != null && !searchQuery.isEmpty()) {
            Page<Classroom> result =  classroomRepository.findClassroomsByNameContainingIgnoreCase(searchQuery, pageable);
            return result.getContent();
        } else {
            Page<Classroom> result =  classroomRepository.findAll(pageable);
            return result.getContent();
        }
    }

    @Override
    public String CreateNewClassroomService(NewClassroomRequest newClassroom) {
        Optional<Classroom> classroom = classroomRepository.findClassroomByNameAndBloc(newClassroom.getRoomName(), newClassroom.getBloc());
        if(classroom.isPresent()) {
            AlreadyExistException alreadyExistException = new AlreadyExistException("classroom with name " + classroom.get().getName() + " is already exist !");
            LOGGER.error("Error while saving new classroom with name {}", classroom.get().getName(), alreadyExistException);
            throw alreadyExistException;
        }
        Classroom classroomToCreate = Classroom.builder()
                .name(newClassroom.getRoomName())
                .bloc(newClassroom.getBloc())
                .capacity(newClassroom.getCapacity())
                .build();

        classroomRepository.save(classroomToCreate);

        return "Classroom has been created successfully";
    }

    @Override
    public String UpdateClassroomService(UUID classroomId, NewClassroomRequest updateClassroomRequest) {
        Optional<Classroom> optionalClassroom = classroomRepository.findById(classroomId);
        if(optionalClassroom.isEmpty()) {
            NotFoundException notFoundException = new NotFoundException("classroom with id " + classroomId + " doesn't exist !");
            LOGGER.error("Error while updating classroom with id {}", classroomId, notFoundException);
            throw notFoundException;
        }
        Classroom originalClassroom = optionalClassroom.get();

        Classroom updatedClassroom = Classroom.builder()
                .id(originalClassroom.getId())
                .name(updateClassroomRequest.getRoomName())
                .bloc(updateClassroomRequest.getBloc())
                .capacity(updateClassroomRequest.getCapacity())
                .build();

        classroomRepository.save(updatedClassroom);

        String successMessge = "Classroom has been updated successfully";
        return successMessge;
    }

    @Override
    public String SoftDeleteClassroomService(UUID classroomId) {
        Optional<Classroom> optionalClassroom = classroomRepository.findById(classroomId);
        if(optionalClassroom.isEmpty()) {
            NotFoundException notFoundException = new NotFoundException("classroom with id " + classroomId + " doesn't exist !");
            LOGGER.error("Error while updating classroom with id {}", classroomId, notFoundException);
            throw notFoundException;
        }

        Classroom originalClassroom = optionalClassroom.get();
        originalClassroom.setDeletedAt(LocalDateTime.now());

        classroomRepository.save(originalClassroom);

        return "Classroom has been deleted successfully";
    }
}
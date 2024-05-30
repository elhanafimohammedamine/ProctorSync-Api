package com.ensah.proctorsync.services.classroom;

import com.ensah.proctorsync.DTOs.classroom.ClassRoomResponse;
import com.ensah.proctorsync.DTOs.classroom.ClassroomUpdateRequest;
import com.ensah.proctorsync.DTOs.classroom.NewClassroomRequest;
import com.ensah.proctorsync.entities.Classroom;
import com.ensah.proctorsync.entities.Monitoring;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IClassroomService {

    Collection<ClassRoomResponse> GetAllClassrooms();
    String CreateNewClassroomService(NewClassroomRequest newClassroomRequest);
    String UpdateClassroomService(UUID classroomId, ClassroomUpdateRequest classroomUpdateRequest);
    String SoftDeleteClassroomService(UUID classroomId);
    Collection<ClassRoomResponse> getAvailableClassrooms(String startDateTime, String endDateTime);
    Optional<Classroom> findClassroomById(UUID id);
    Classroom save(Classroom classroom);

}

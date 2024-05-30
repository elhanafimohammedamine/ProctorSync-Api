package com.ensah.proctorsync.services.classroom;

import com.ensah.proctorsync.DTOs.classroom.ClassRoomResponse;
import com.ensah.proctorsync.DTOs.classroom.ClassroomUpdateRequest;
import com.ensah.proctorsync.DTOs.classroom.NewClassroomRequest;
import com.ensah.proctorsync.entities.Classroom;

import java.util.Collection;
import java.util.UUID;

public interface IClassroomService {

    Collection<ClassRoomResponse> GetAllClassrooms(String searchQuery, int page, int pageSize);
    String CreateNewClassroomService(NewClassroomRequest newClassroomRequest);
    String UpdateClassroomService(UUID classroomId, ClassroomUpdateRequest classroomUpdateRequest);
    String SoftDeleteClassroomService(UUID classroomId);

}

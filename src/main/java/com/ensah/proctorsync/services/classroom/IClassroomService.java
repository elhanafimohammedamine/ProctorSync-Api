package com.ensah.proctorsync.services.classroom;

import com.ensah.proctorsync.entities.Classroom;

import java.util.Collection;
import java.util.UUID;

public interface IClassroomService {

    public Collection<Classroom> GetAllClassrooms(String searchQuery, int page, int pageSize);
    public String CreateNewClassroomService(com.ensah.proctorsync.DTOs.Classroom.NewClassroomRequest newClassroom);
    public String UpdateClassroomService(UUID classroomId, com.ensah.proctorsync.DTOs.Classroom.NewClassroomRequest newClassroom);
    public String SoftDeleteClassroomService(UUID classroomId);

}

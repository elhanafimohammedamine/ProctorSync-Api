package com.ensah.proctorsync.services.classroom;

import com.ensah.proctorsync.DTOs.classroom.NewClassroomRequest;
import com.ensah.proctorsync.entities.Classroom;

import java.util.Collection;
import java.util.UUID;

public interface IClassroomService {

    public Collection<Classroom> GetAllClassrooms(String searchQuery, int page, int pageSize);
    public String CreateNewClassroomService(NewClassroomRequest newClassroomRequest);
    public String UpdateClassroomService(UUID classroomId, NewClassroomRequest newClassroomRequest);
    public String SoftDeleteClassroomService(UUID classroomId);

}

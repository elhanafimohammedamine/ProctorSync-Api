package com.ensah.proctorsync.services.Classroom;

import com.ensah.proctorsync.DTOs.Classroom.NewClassroom;
import com.ensah.proctorsync.entities.Classroom;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.UUID;

public interface IClassroomService {

    public Collection<Classroom> GetAllClassrooms(String searchQuery, int page, int pageSize);
    public String CreateNewClassroomService(NewClassroom newClassroom);
    public String UpdateClassroomService(UUID classroomId, NewClassroom newClassroom);
    public String SoftDeleteClassroomService(UUID classroomId);

}

package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.classroom.ClassRoomResponse;
import com.ensah.proctorsync.entities.Classroom;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IClassRoomMapper {
    ClassRoomResponse classRoomToClassRoomResponse(Classroom classroom);
    Collection<ClassRoomResponse> classRoomsToClassRoomsResponse(Collection<Classroom> classrooms);

}

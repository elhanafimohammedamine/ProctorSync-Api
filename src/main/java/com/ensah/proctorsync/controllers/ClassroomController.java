package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.classroom.ClassRoomResponse;
import com.ensah.proctorsync.DTOs.classroom.ClassroomUpdateRequest;
import com.ensah.proctorsync.DTOs.classroom.NewClassroomRequest;
import com.ensah.proctorsync.entities.Classroom;
import com.ensah.proctorsync.services.classroom.IClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classroom")
@RequiredArgsConstructor
public class ClassroomController {
    private final IClassroomService classroomService;

    @GetMapping
    public ResponseEntity<Collection<ClassRoomResponse>> GetClassrooms(){
        return ResponseEntity.ok(classroomService.GetAllClassrooms());
    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateClassroom(@Valid  @RequestBody NewClassroomRequest newClassroom) {
        return ResponseEntity.ok(classroomService.CreateNewClassroomService(newClassroom));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> UpdateClassroom(@PathVariable UUID id, @Valid  @RequestBody ClassroomUpdateRequest updateClassroom) {
        return ResponseEntity.ok(classroomService.UpdateClassroomService(id, updateClassroom));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteClassroom(@PathVariable UUID id) {
        return ResponseEntity.ok(classroomService.SoftDeleteClassroomService(id));
    }

    @GetMapping("/available")
    public ResponseEntity<Collection<ClassRoomResponse>> getAvailableClassrooms(
            @RequestParam String startDateTime,
            @RequestParam String endDateTime
    ) {
        return ResponseEntity.ok(classroomService.getAvailableClassrooms(startDateTime, endDateTime));
    }
}

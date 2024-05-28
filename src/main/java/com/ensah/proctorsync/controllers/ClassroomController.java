package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.entities.Classroom;
import com.ensah.proctorsync.services.classroom.IClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classroom")
@RequiredArgsConstructor
public class ClassroomController {
    private final IClassroomService classroomService;

    @GetMapping("/classrooms")
    public ResponseEntity<Collection<Classroom>> GetClassrooms(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int pageSize){
        return ResponseEntity.ok(classroomService.GetAllClassrooms(query, page, pageSize));
    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateClassroom(@Valid  @RequestBody com.ensah.proctorsync.DTOs.Classroom.NewClassroomRequest newClassroom) {
        return ResponseEntity.ok(classroomService.CreateNewClassroomService(newClassroom));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> UpdateClassroom(@PathVariable UUID id, @Valid  @RequestBody com.ensah.proctorsync.DTOs.Classroom.NewClassroomRequest updateClassroom) {
        return ResponseEntity.ok(classroomService.UpdateClassroomService(id, updateClassroom));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteClassroom(@PathVariable UUID id) {
        return ResponseEntity.ok(classroomService.SoftDeleteClassroomService(id));
    }
}

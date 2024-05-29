package com.ensah.proctorsync.controllers;


import com.ensah.proctorsync.DTOs.group.GroupResponse;
import com.ensah.proctorsync.DTOs.group.GroupUpdateRequest;
import com.ensah.proctorsync.DTOs.group.NewGroupRequest;
import com.ensah.proctorsync.services.group.IGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final IGroupService groupService;

    @GetMapping
    public ResponseEntity<Collection<GroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getGroupById(@PathVariable UUID id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@RequestBody @Valid NewGroupRequest newGroupRequest) {
        return ResponseEntity.ok(groupService.save(newGroupRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateGroup(
            @PathVariable UUID id,
            @RequestBody @Valid GroupUpdateRequest groupUpdateRequest
            ) {
        return ResponseEntity.ok(groupService.update(id, groupUpdateRequest));
    }

    @PostMapping("add-members/{id}")
    public ResponseEntity<String> addMembers(@PathVariable UUID id, @RequestBody Collection<UUID> professorsIds) {
        return ResponseEntity.ok(groupService.addProfessorsToGroup(id, professorsIds));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable UUID id) {
        return ResponseEntity.ok(groupService.delete(id));
    }
}

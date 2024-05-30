package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.level.LevelResponse;
import com.ensah.proctorsync.services.level.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/level")
@RequiredArgsConstructor
public class LevelController {

    private final ILevelService levelService;


    @GetMapping
    public ResponseEntity<Collection<LevelResponse>> getAllLevels() {
        return ResponseEntity.ok(levelService.getAllLevels());
    }

}

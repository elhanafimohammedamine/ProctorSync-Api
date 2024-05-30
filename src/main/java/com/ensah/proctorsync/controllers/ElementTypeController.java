package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.DTOs.elementype.ElementTypeResponse;
import com.ensah.proctorsync.services.elementype.IElementTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/element-type")
@RequiredArgsConstructor
public class ElementTypeController {
    private final IElementTypeService elementTypeService;


    @GetMapping
    public ResponseEntity<Collection<ElementTypeResponse>> getAllElementsType() {
        return ResponseEntity.ok(elementTypeService.getAllElementTypes());
    }

}

package com.ensah.proctorsync.services.elementype;

import com.ensah.proctorsync.DTOs.elementype.ElementTypeResponse;
import com.ensah.proctorsync.entities.ElementType;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface IElementTypeService {
    Optional<ElementType> findElementById(UUID id);
    Collection<ElementTypeResponse> getAllElementTypes();
}

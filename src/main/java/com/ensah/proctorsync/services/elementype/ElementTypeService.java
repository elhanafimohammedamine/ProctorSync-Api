package com.ensah.proctorsync.services.elementype;

import com.ensah.proctorsync.DTOs.elementype.ElementTypeResponse;
import com.ensah.proctorsync.entities.ElementType;
import com.ensah.proctorsync.mappers.IElementTypeMapper;
import com.ensah.proctorsync.repositories.elementype.IElementTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ElementTypeService implements IElementTypeService {
    private final IElementTypeRepository elementTypeRepository;
    private final IElementTypeMapper elementTypeMapper;
    @Override
    public Optional<ElementType> findElementById(UUID id) {
        return elementTypeRepository.findById(id);
    }

    @Override
    public Collection<ElementTypeResponse> getAllElementTypes() {
        return elementTypeMapper.elementsTypeToElementsTypeResponse(
                elementTypeRepository.findAll()
        );
    }
}

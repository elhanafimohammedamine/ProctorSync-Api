package com.ensah.proctorsync.mappers;


import com.ensah.proctorsync.DTOs.elementype.ElementTypeResponse;
import com.ensah.proctorsync.entities.ElementType;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IElementTypeMapper {
    ElementTypeResponse elementTypeToElementTypeResponse(ElementType elementType);
    Collection<ElementTypeResponse> elementsTypeToElementsTypeResponse(Collection<ElementType> elementTypes);
}

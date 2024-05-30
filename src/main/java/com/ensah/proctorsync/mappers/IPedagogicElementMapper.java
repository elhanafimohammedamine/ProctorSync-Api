package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementResponse;
import com.ensah.proctorsync.entities.PedagogicElement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.Collection;

@Mapper(componentModel = "spring")
public interface IPedagogicElementMapper {
    @Mapping(source = "level", target = "level")
    PedagogicElementResponse pedagogicElementToPedagogicElementResponse(PedagogicElement pedagogicElement);
    Collection<PedagogicElementResponse> pedagogicElementsToPedagogicElementsResponse(Collection<PedagogicElement> pedagogicElements);

}

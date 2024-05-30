package com.ensah.proctorsync.services.pedagogicelement;

import com.ensah.proctorsync.DTOs.pedagogicelement.NewPedagogicElementRequest;
import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementResponse;
import com.ensah.proctorsync.DTOs.pedagogicelement.PedagogicElementUpdateRequest;
import com.ensah.proctorsync.entities.PedagogicElement;

import java.util.Collection;
import java.util.UUID;

public interface IPedagogicElementService {
    String save(NewPedagogicElementRequest newPedagogicElementRequest);
    String update(UUID pedagogicElementId , PedagogicElementUpdateRequest pedagogicElementUpdateRequest);
    String delete(UUID pedagogicElementId);
    PedagogicElementResponse getPedagogicElementById(UUID pedagogicElementId);
    Collection<PedagogicElementResponse> getAllPedagogicElements();
}

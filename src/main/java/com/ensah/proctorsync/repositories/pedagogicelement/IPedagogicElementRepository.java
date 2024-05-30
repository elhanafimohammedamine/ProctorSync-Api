package com.ensah.proctorsync.repositories.pedagogicelement;


import com.ensah.proctorsync.entities.PedagogicElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPedagogicElementRepository extends JpaRepository<PedagogicElement, UUID> {

}

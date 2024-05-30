package com.ensah.proctorsync.repositories.elementype;

import com.ensah.proctorsync.entities.ElementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IElementTypeRepository extends JpaRepository<ElementType, UUID> {
}

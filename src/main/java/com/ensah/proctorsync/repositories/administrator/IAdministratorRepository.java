package com.ensah.proctorsync.repositories.administrator;

import com.ensah.proctorsync.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAdministratorRepository extends JpaRepository<Administrator, UUID> {
    Optional<Administrator> findByEmail(String email);
    boolean existsByEmail(String email);
}

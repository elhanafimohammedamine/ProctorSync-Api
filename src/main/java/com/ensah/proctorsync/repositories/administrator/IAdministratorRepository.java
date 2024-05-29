package com.ensah.proctorsync.repositories.administrator;

import com.ensah.proctorsync.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IAdministratorRepository extends JpaRepository<Administrator, UUID> {
    Optional<Administrator> findByEmail(String email);
    boolean existsByEmail(String email);
}

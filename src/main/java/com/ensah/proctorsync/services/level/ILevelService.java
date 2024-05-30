package com.ensah.proctorsync.services.level;

import com.ensah.proctorsync.DTOs.level.LevelResponse;
import com.ensah.proctorsync.entities.Level;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ILevelService {
    Optional<Level> findByLevelById(UUID levelId);
    Collection<LevelResponse> getAllLevels();
}

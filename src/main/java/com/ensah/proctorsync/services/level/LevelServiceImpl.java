package com.ensah.proctorsync.services.level;

import com.ensah.proctorsync.DTOs.level.LevelResponse;
import com.ensah.proctorsync.entities.Level;
import com.ensah.proctorsync.mappers.ILevelMapper;
import com.ensah.proctorsync.repositories.level.ILevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements ILevelService {

    private final ILevelRepository levelRepository;
    private final ILevelMapper levelMapper;
    @Override
    public Optional<Level> findByLevelById(UUID levelId) {
        return levelRepository.findById(levelId);
    }

    @Override
    public Collection<LevelResponse> getAllLevels() {
        return levelMapper.levelsToLevelsResponse(
                levelRepository.findAll()
        );
    }

}

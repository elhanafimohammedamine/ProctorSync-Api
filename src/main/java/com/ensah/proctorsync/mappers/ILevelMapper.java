package com.ensah.proctorsync.mappers;

import com.ensah.proctorsync.DTOs.level.LevelResponse;
import com.ensah.proctorsync.entities.Level;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ILevelMapper {
    LevelResponse levelToLevelResponse(Level level);
    Collection<LevelResponse> levelsToLevelsResponse(Collection<Level> levels);
}

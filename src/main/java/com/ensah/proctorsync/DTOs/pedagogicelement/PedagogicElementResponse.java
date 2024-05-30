package com.ensah.proctorsync.DTOs.pedagogicelement;

import com.ensah.proctorsync.DTOs.elementype.ElementTypeResponse;
import com.ensah.proctorsync.DTOs.level.LevelResponse;
import com.ensah.proctorsync.DTOs.professor.ProfessorResponse;
import com.ensah.proctorsync.entities.ElementType;
import com.ensah.proctorsync.entities.Level;
import com.ensah.proctorsync.entities.Professor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PedagogicElementResponse {

    private UUID id;
    private String elementTitle;
    private LevelResponse level;
    private ElementTypeResponse elementType;
    private ProfessorResponse professor;
    private ProfessorResponse coordinator;

}

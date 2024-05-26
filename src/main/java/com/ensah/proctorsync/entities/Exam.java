package com.ensah.proctorsync.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor @ToString
public class Exam {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate startedOn;
    private LocalTime expectStartTime;
    private LocalTime realStartTime;
    private String testFilePath;
    private String pvFilePath;
    private String report;

}

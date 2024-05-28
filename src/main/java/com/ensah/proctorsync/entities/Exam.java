package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
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

    @OneToMany(mappedBy = "exam")
    private Collection<Monitoring> monitoring;

    @ManyToOne
    private Session session;

    @ManyToOne
    private ExamType examType;

    @ManyToOne
    private Semester semester;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

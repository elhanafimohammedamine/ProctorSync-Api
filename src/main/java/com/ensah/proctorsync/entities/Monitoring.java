package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor  @AllArgsConstructor @ToString
public class Monitoring {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany
    private Collection<Professor> professors;

    @ManyToOne
    private Professor coordinator;

    @ManyToOne
    private Administrator administrator;

    @ManyToOne
    private Classroom classroom;

    @ManyToOne
    private Exam exam;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Classroom {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String bloc;
    private int capacity;

    @OneToMany(
            mappedBy = "classroom",
            fetch = FetchType.LAZY
    )
    private Collection<Monitoring> monitoring;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class PedagogicElement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String elementTitle;

    @ManyToOne(fetch = FetchType.EAGER)
    private Level level;

    @ManyToOne(fetch = FetchType.EAGER)
    private ElementType elementType;

    @ManyToOne
    private Professor professor;

    @ManyToOne @JoinColumn(name = "coordinator_id")
    private Professor coordinator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}


package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String branchName;

    @OneToMany(mappedBy = "branch")
    private Collection<Professor> professors;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


}


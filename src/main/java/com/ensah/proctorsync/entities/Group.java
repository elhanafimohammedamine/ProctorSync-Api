package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@ToString
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "`groups`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String groupName;
    private String description;
    @OneToMany(
            mappedBy = "group",
            fetch = FetchType.EAGER
    )
    private Collection<Professor> professors;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}

package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.UUID;


@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @ToString
public class Classroom {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int capacity;

    @OneToMany(mappedBy = "classroom")
    private Collection<Monitoring> monitoring;

}

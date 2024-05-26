package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor  @AllArgsConstructor @ToString
public class Monitoring {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}

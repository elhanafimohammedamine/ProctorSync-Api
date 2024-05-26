package com.ensah.proctorsync.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(
            mappedBy = "roles",
            fetch = FetchType.EAGER
    )
    private Collection<User> users;

}

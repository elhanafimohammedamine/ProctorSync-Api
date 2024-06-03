package com.ensah.proctorsync.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor @ToString
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(
            mappedBy = "roles",
            fetch = FetchType.EAGER
    )
    private Collection<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}

package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class Professor extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    @ManyToMany(mappedBy = "professors")
    private Collection<Monitoring> monitoring;

    @OneToMany(mappedBy = "coordinator")
    private Collection<Monitoring> coordinatingMonitoring;

}

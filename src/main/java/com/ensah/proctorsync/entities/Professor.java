package com.ensah.proctorsync.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Collection;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
public class Professor extends User {

    @ManyToOne
    private Group group;

    @ManyToOne
    private Branch branch;

    @ManyToOne
    private Department department;

    @ManyToMany(mappedBy = "professors")
    private Collection<Monitoring> monitoring;

    @OneToMany(mappedBy = "coordinator")
    private Collection<Monitoring> coordinatingMonitoring;


}

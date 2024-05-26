package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
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

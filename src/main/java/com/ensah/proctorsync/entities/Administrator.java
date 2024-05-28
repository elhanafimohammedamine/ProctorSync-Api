package com.ensah.proctorsync.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
public class Administrator extends User{

    @OneToMany(mappedBy = "administrator")
    private Collection<Monitoring> monitoring;


}

package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by khartv on 21.11.2016.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "pesel")
    private Person person;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(targetEntity = Visit.class, mappedBy = "patient")
    private List<Visit> visits = newArrayList();


}

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
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "pesel")
    private Person person;
    @Column(name = "active")
    private Boolean active;

    @OneToOne(targetEntity = Doctor.class, mappedBy = "employee")
    private Doctor doctor;

    @OneToMany(targetEntity = Hire.class, mappedBy = "employee")
    private List<Hire> hiresOfEmployee = newArrayList();

    @OneToMany(targetEntity = Hire.class, mappedBy = "supervisor")
    private List<Hire> supervisedHires = newArrayList();

}

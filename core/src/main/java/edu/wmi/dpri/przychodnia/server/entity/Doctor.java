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
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany
    @JoinTable(name = "doctors_specialisations", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "specialisation_id"))
    private List<Specialisation> specialisations = newArrayList();

    @OneToMany(targetEntity = Visit.class, mappedBy = "doctor")
    private List<Visit> visits = newArrayList();

    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;

    @ManyToMany
    @JoinTable(name = "doctors_availabilities", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "availability_id"))
    private List<Availability> availabilities = newArrayList();
}

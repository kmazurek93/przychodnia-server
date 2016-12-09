package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "specialisations")
public class Specialisation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany
    @JoinTable(name = "doctors_specialisations", joinColumns = @JoinColumn(name = "specialisation_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctors;


}

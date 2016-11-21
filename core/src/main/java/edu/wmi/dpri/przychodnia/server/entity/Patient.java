package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */

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

    @OneToMany(targetEntity = Visit.class, mappedBy = "patient")
    private List<Visit> visits;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }


}

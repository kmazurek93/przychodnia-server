package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kmazu on 12.06.2016.
 */
@Entity
@Table(name = "sex")
public class Sex {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(targetEntity = Person.class, mappedBy = "sex")
    private List<Person> persons;

    public Sex() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}

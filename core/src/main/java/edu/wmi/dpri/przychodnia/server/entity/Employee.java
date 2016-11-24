package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */
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

    @OneToOne(targetEntity = Doctor.class, mappedBy = "employee")
    private Doctor doctor;

    @OneToMany(targetEntity = Hire.class, mappedBy = "employee")
    private List<Hire> hiresOfEmployee;

    @OneToMany(targetEntity = Hire.class, mappedBy = "supervisor")
    private List<Hire> supervisedHires;

    public Employee() {

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Hire> getHiresOfEmployee() {
        return hiresOfEmployee;
    }

    public void setHiresOfEmployee(List<Hire> hiresOfEmployee) {
        this.hiresOfEmployee = hiresOfEmployee;
    }

    public List<Hire> getSupervisedHires() {
        return supervisedHires;
    }

    public void setSupervisedHires(List<Hire> supervisedHires) {
        this.supervisedHires = supervisedHires;
    }
}

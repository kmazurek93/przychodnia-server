package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "doctors_specialisations", joinColumns = @JoinColumn(name = "doctor_id"), inverseJoinColumns = @JoinColumn(name = "specialisation_id"))
    private List<Specialisation> specialisations;

    @OneToMany(targetEntity = Visit.class, mappedBy = "doctor")
    private List<Visit> visits;

    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToMany(mappedBy = "doctors", targetEntity = Doctor.class)
    @JoinTable(name = "doctor_availability",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "availability_id"))
    private List<Availability> availabilities;

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Specialisation> getSpecialisations() {
        return specialisations;
    }

    public void setSpecialisations(List<Specialisation> specialisations) {
        this.specialisations = specialisations;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}

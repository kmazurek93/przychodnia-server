package edu.wmi.dpri.przychodnia.server.entity;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime date;

    @Column(name = "window", nullable = false)
    private Integer window;

    @Column(name = "happened")
    private Boolean happened;

    @ManyToOne(targetEntity = Visit.class)
    @JoinColumn(name = "associated_visit")
    private Visit associatedVisit;

    @OneToMany(targetEntity = Visit.class, mappedBy = "associatedVisit")
    private List<Visit> visitsAssociatedWith;

    @Lob
    @Column(name = "comment")
    private String comment;

    public Visit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Integer getWindow() {
        return window;
    }

    public void setWindow(Integer window) {
        this.window = window;
    }

    public Boolean isHappened() {
        return happened;
    }

    public Boolean getHappened() {
        return happened;
    }

    public void setHappened(Boolean happened) {
        this.happened = happened;
    }

    public Visit getAssociatedVisit() {
        return associatedVisit;
    }

    public void setAssociatedVisit(Visit associatedVisit) {
        this.associatedVisit = associatedVisit;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Visit> getVisitsAssociatedWith() {
        return visitsAssociatedWith;
    }

    public void setVisitsAssociatedWith(List<Visit> visitsAssociatedWith) {
        this.visitsAssociatedWith = visitsAssociatedWith;
    }
}

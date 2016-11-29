package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.VisitStatusType;
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

    @ManyToOne(targetEntity = TimeWindow.class)
    @JoinColumn(name = "time_window_id")
    private TimeWindow timeWindow;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime date;

    @Enumerated
    @Column(name = "status")
    private VisitStatusType status;

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

    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public VisitStatusType getStatus() {
        return status;
    }

    public void setStatus(VisitStatusType status) {
        this.status = status;
    }
}

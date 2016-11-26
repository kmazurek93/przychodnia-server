package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lupus on 26.11.16.
 */
@Entity
@Table(name = "availabilities")
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weekday")
    private String weekday;

    @ManyToMany(mappedBy = "availabilities", targetEntity = Doctor.class)
    @JoinTable(name="doctor_availability",
            joinColumns = @JoinColumn(name = "availability_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctors;

    @ManyToOne(targetEntity = TimeWindow.class)
    @JoinColumn(name = "begin_window")
    private TimeWindow beginWindow;

    @JoinColumn(name = "end_window")
    @ManyToOne(targetEntity = TimeWindow.class)
    private TimeWindow endWindow;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public TimeWindow getBeginWindow() {
        return beginWindow;
    }

    public void setBeginWindow(TimeWindow beginWindow) {
        this.beginWindow = beginWindow;
    }

    public TimeWindow getEndWindow() {
        return endWindow;
    }

    public void setEndWindow(TimeWindow endWindow) {
        this.endWindow = endWindow;
    }

}

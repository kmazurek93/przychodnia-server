package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.WeekdayType;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 * Created by khartv on 29.11.2016.
 */

@Entity
@Table(name = "availabilities")
public class Availability {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated
    @Column(name="weekday")
    WeekdayType weekday;

    @ManyToOne(targetEntity = TimeWindow.class)
    @JoinColumn(name = "start_time_window")
    TimeWindow startTimeWindow;

    @ManyToOne(targetEntity = TimeWindow.class)
    @JoinColumn(name = "end_time_window")
    TimeWindow endTimeWindow;

    @Column(name = "date_start", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime dateStart;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime dateEnd;

    @ManyToMany
    @JoinTable(name = "doctors_availabilities", joinColumns = @JoinColumn(name = "availability_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctors;


    public Availability() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeekdayType getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekdayType weekday) {
        this.weekday = weekday;
    }

    public TimeWindow getStartTimeWindow() {
        return startTimeWindow;
    }

    public void setStartTimeWindow(TimeWindow startTimeWindow) {
        this.startTimeWindow = startTimeWindow;
    }

    public TimeWindow getEndTimeWindow() {
        return endTimeWindow;
    }

    public void setEndTimeWindow(TimeWindow endTimeWindow) {
        this.endTimeWindow = endTimeWindow;
    }

    public DateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(DateTime dateStart) {
        this.dateStart = dateStart;
    }

    public DateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(DateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}

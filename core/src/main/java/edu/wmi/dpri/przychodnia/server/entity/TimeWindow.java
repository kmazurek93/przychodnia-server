package edu.wmi.dpri.przychodnia.server.entity;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 29.11.2016.
 */
@Entity
@Table(name="time_windows")
public class TimeWindow {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="order", nullable = false)
    Integer order;

    @Column(name="start_time", nullable = false)
    @DateTimeFormat(pattern = "hh24:mi")
    private DateTime startTime;

    @Column(name="end_time", nullable = false)
    @DateTimeFormat(pattern = "hh24:mi")
    private DateTime endTime;

    @OneToMany(targetEntity = Visit.class, mappedBy = "timeWindow")
    List<Visit> visitsInTimewindow;

    @OneToMany(targetEntity = Availability.class, mappedBy = "startTimeWindow")
    List<Availability> AvailabilitiesStartingInTimewindow;

    @OneToMany(targetEntity = Availability.class, mappedBy = "endTimeWindow")
    List<Availability> AvailabilitiesEndingInTimewindow;

    public TimeWindow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public List<Visit> getVisitsInTimewindow() {
        return visitsInTimewindow;
    }

    public void setVisitsInTimewindow(List<Visit> visitsInTimewindow) {
        this.visitsInTimewindow = visitsInTimewindow;
    }

    public List<Availability> getAvailabilitiesStartingInTimewindow() {
        return AvailabilitiesStartingInTimewindow;
    }

    public void setAvailabilitiesStartingInTimewindow(List<Availability> availabilitiesStartingInTimewindow) {
        AvailabilitiesStartingInTimewindow = availabilitiesStartingInTimewindow;
    }

    public List<Availability> getAvailabilitiesEndingInTimewindow() {
        return AvailabilitiesEndingInTimewindow;
    }

    public void setAvailabilitiesEndingInTimewindow(List<Availability> availabilitiesEndingInTimewindow) {
        AvailabilitiesEndingInTimewindow = availabilitiesEndingInTimewindow;
    }
}

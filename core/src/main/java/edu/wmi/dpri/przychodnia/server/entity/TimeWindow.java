package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 29.11.2016.
 */

@Data
@NoArgsConstructor
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

}

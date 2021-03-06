package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;



@Data
@NoArgsConstructor
@Entity
@Table(name = "time_windows")
public class TimeWindow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "order", nullable = false)
    Integer order;

    @Column(name = "start_time", nullable = false, columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false, columnDefinition = "TIME")
    private LocalTime endTime;

    @OneToMany(targetEntity = Visit.class, mappedBy = "timeWindow")
    List<Visit> visitsInTimewindow = newArrayList();

    @OneToMany(targetEntity = Availability.class, mappedBy = "startTimeWindow")
    List<Availability> AvailabilitiesStartingInTimewindow = newArrayList();

    @OneToMany(targetEntity = Availability.class, mappedBy = "endTimeWindow")
    List<Availability> AvailabilitiesEndingInTimewindow = newArrayList();

}

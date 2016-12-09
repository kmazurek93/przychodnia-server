package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.WeekdayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

/**
 * Created by khartv on 29.11.2016.
 */


@Data
@NoArgsConstructor
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
}

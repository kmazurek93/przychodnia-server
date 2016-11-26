package edu.wmi.dpri.przychodnia.server.entity;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lupus on 26.11.16.
 */
@Entity
@Table(name = "time_windows")
public class TimeWindow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order", unique = true, nullable = false)
    private Integer order;

    @Column(name = "start", nullable = false)
    private DateTime start;

    @Column(name = "end", nullable = false)
    private DateTime end;

    @OneToMany(mappedBy = "startWindow", targetEntity = Availability.class)
    private List<Availability> availabilitiesThatUsesAsStartWindow;

    @OneToMany(mappedBy = "endWindow", targetEntity = Availability.class)
    private List<Availability> availabilitiesThatUsesAsEndWindow;


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

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public List<Availability> getAvailabilitiesThatUsesAsStartWindow() {
        return availabilitiesThatUsesAsStartWindow;
    }

    public void setAvailabilitiesThatUsesAsStartWindow(List<Availability> availabilitiesThatUsesAsStartWindow) {
        this.availabilitiesThatUsesAsStartWindow = availabilitiesThatUsesAsStartWindow;
    }

    public List<Availability> getAvailabilitiesThatUsesAsEndWindow() {
        return availabilitiesThatUsesAsEndWindow;
    }

    public void setAvailabilitiesThatUsesAsEndWindow(List<Availability> availabilitiesThatUsesAsEndWindow) {
        this.availabilitiesThatUsesAsEndWindow = availabilitiesThatUsesAsEndWindow;
    }
}

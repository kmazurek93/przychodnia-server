package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.server.entity.enums.VisitStatusType;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

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
}

package edu.wmi.dpri.przychodnia.server.entity;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;



@Data
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
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
    private LocalDate date;

    @Enumerated
    @Column(name = "status")
    private VisitStatusType status;

    @ManyToOne(targetEntity = Visit.class)
    @JoinColumn(name = "associated_visit")
    private Visit associatedVisit;

    @OneToMany(targetEntity = Visit.class, mappedBy = "associatedVisit")
    private List<Visit> visitsAssociatedWith = newArrayList();

    @Lob
    @Column(name = "comment")
    private String comment;
}

package edu.wmi.dpri.przychodnia.server.entity.procedures;

import lombok.Data;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by khartv on 23.12.2016.
 */
@NamedStoredProcedureQuery(
        name = "getCalendarOfADoctor",
        procedureName = "doctor_calendar",
        resultClasses = {DoctorCalendar.class},
        parameters = {
                @StoredProcedureParameter(name = "p_doctor_id", type = Long.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_date_start", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_date_end", type = String.class, mode = ParameterMode.IN)
        }
)
@Entity
@Table(name = "doctor_calendar_t")
@Data
public class DoctorCalendar implements Serializable {

    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "_DATE")
    LocalDate date;
    @Column(name = "tw_order")
    Integer order;
    @Column(name = "tw_start_time")
    LocalTime startTime;
    @Column(name = "tw_end_time")
    LocalTime endTime;
    @Column(name = "visit_id")
    Long visit_id;
    @Column(name = "state")
    String state;
}

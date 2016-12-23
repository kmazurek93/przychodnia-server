package edu.wmi.dpri.przychodnia.server.entity.procedures;

import lombok.Getter;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.persistence.*;

/**
 * Created by khartv on 23.12.2016.
 */

@NamedStoredProcedureQuery(
        name="getCalendarOfADoctor",
        procedureName="doctor_calendar",
        resultClasses = { DoctorCalendar.class },
        parameters={
                @StoredProcedureParameter(name="p_doctor_id", type=Integer.class, mode= ParameterMode.IN),
                @StoredProcedureParameter(name="p_date_start", type=LocalDate.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_date_end", type=LocalDate.class, mode=ParameterMode.IN)
        }
)
public class DoctorCalendar {

    @Id
    @Getter LocalDate date;
    @Getter Integer order;
    @Getter LocalTime startTime;
    @Getter LocalTime endTime;
    @Getter Integer visit_id;
    @Getter String state;
}

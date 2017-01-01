package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.CalendarRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.entity.procedures.DoctorCalendar;
import edu.wmi.dpri.przychodnia.server.visits.function.DoctorCalendarToSimpleAvailabilityModelFunction;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class DoctorCalendarService {

    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private DoctorCalendarToSimpleAvailabilityModelFunction function;


    public List<SimpleAvailabilityWebModel> getDoctorsCalendar(CalendarRequestModel model) {
        List<DoctorCalendar> results = getDoctorCalendar(model);
        return function.convertAll(results);
    }

    @SuppressWarnings("unchecked")
    public List<DoctorCalendar> getDoctorCalendar(CalendarRequestModel model) {
        StoredProcedureQuery query = entityManager
                .createNamedStoredProcedureQuery("getCalendarOfADoctor");

        query.setParameter("p_doctor_id", model.getDoctorId());
        query.setParameter("p_date_start", getStringDate(model.getStartDate()));
        query.setParameter("p_date_end", getStringDate(model.getEndDate()));
        query.execute();
        return (List<DoctorCalendar>) query.getResultList();
    }

    private String getStringDate(Long instant) {
        return newDateTime(instant).toString("yyyy-MM-dd");
    }

    private DateTime newDateTime(Long instant) {
        return new DateTime(instant);
    }
}

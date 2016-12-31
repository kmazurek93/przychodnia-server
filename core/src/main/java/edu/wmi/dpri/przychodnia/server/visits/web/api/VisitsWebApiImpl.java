package edu.wmi.dpri.przychodnia.server.visits.web.api;

import edu.wmi.dpri.przychodnia.commons.visits.webapi.VisitsWebApi;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.*;
import edu.wmi.dpri.przychodnia.server.visits.web.service.DoctorAvailabilityWebService;
import edu.wmi.dpri.przychodnia.server.visits.web.service.DoctorCalendarWebService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by lupus on 17.12.16.
 */
public class VisitsWebApiImpl implements VisitsWebApi {
    @Inject
    private DoctorAvailabilityWebService doctorAvailabilityWebService;
    @Inject
    private DoctorCalendarWebService doctorCalendarWebService;

    @Override
    public List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model) {
        return doctorAvailabilityWebService.getNextAvailableMonthsForDoctor(model);
    }

    @Override
    public List<DayWebModel> getAvailableDaysForDoctorsMonth(AvailableTimeRequestModel model) {
        return doctorAvailabilityWebService.getDayAvailabilityForDoctorAndMonth(model);
    }


    @Override
    public Map<String, List<SimpleAvailabilityWebModel>> getCalendarForDoctor(AvailableTimeRequestModel model) {
        return doctorCalendarWebService.getDoctorCalendar(model);
    }
}

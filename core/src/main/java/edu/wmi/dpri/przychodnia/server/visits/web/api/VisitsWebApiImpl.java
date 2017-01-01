package edu.wmi.dpri.przychodnia.server.visits.web.api;

import edu.wmi.dpri.przychodnia.commons.visits.webapi.VisitsWebApi;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.*;
import edu.wmi.dpri.przychodnia.server.visits.web.service.DoctorAvailabilityWebService;
import edu.wmi.dpri.przychodnia.server.visits.web.service.DoctorCalendarWebService;
import edu.wmi.dpri.przychodnia.server.visits.web.service.VisitsWebService;

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
    @Inject
    private VisitsWebService visitsWebService;

    @Override
    public List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model) {
        return doctorAvailabilityWebService.getNextAvailableMonthsForDoctor(model);
    }

    @Override
    public List<DayWebModel> getAvailableDaysForDoctorsMonth(AvailableTimeRequestModel model) {
        return doctorAvailabilityWebService.getDayAvailabilityForDoctorAndMonth(model);
    }


    @Override
    public Map<String, List<SimpleAvailabilityWebModel>> getCalendarForDoctor(CalendarRequestModel model) {
        return doctorCalendarWebService.getDoctorCalendar(model);
    }

    @Override
    public List<SimpleVisitWebModel> getAllOwnVisits(VisitQueryModel model) {
        return visitsWebService.getOwnVisits(model);
    }

    @Override
    public SimpleVisitWebModel createNewVisit(SimpleVisitWebModel visitRequest) {
        return visitsWebService.createNewVisit(visitRequest);
    }

    @Override
    public SimpleVisitWebModel alterVisitStatus(Long visitId, VisitStatusChangeModel visitStatusChangeModel) {
        return null;
    }

    @Override
    public FullVisitWebModel getVisitDetails(Long visitId) {
        return null;
    }

    @Override
    public FullVisitWebModel alterVisit(Long visitId, FullVisitWebModel fullVisitWebModel) {
        return null;
    }

    @Override
    public SimpleVisitWebModel changeVisitDate(VisitDateChangeModel model) {
        return null;
    }

    @Override
    public void removeVisit(Long id) {

    }
}

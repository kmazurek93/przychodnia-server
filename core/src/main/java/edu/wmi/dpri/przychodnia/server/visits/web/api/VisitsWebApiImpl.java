package edu.wmi.dpri.przychodnia.server.visits.web.api;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.SimplePage;
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
    public SimplePage<SimpleVisitWebModel> getAllOwnVisits(VisitQueryModel model) {
        return visitsWebService.getOwnVisits(model);
    }

    @Override
    public PatientHistoryPage getPatientsVisits(PatientHistoryQueryModel model) {
        return visitsWebService.getPatientHistory(model);
    }

    @Override
    public SimpleVisitWebModel createNewVisit(SimpleVisitWebModel visitRequest) {
        return visitsWebService.createNewVisit(visitRequest);
    }

    @Override
    public SimpleVisitWebModel alterVisitStatus(Long visitId, VisitStatusChangeModel visitStatusChangeModel) {
        return visitsWebService.changeStatus(visitId, visitStatusChangeModel);
    }

    @Override
    public FullVisitWebModel getVisitDetails(Long visitId) {
        return visitsWebService.getVisitDetails(visitId);
    }

    @Override
    public FullVisitWebModel alterVisit(Long visitId, FullVisitWebModel fullVisitWebModel) {
        return visitsWebService.alterVisit(visitId, fullVisitWebModel);
    }

    @Override
    public SimpleVisitWebModel changeVisitDate(VisitDateChangeModel model) {
        return visitsWebService.changeVisitDate(model);
    }

    @Override
    public void removeVisit(Long id) {
        visitsWebService.removeVisit(id);
    }
}

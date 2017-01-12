package edu.wmi.dpri.przychodnia.client.rest.visits;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.SimplePage;
import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.visits.webapi.VisitsWebApi;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.*;

import java.util.List;
import java.util.Map;

public class VisitClient extends GenericRestClient<VisitsWebApi> {
    public VisitClient(String url) {
        super(url, VisitsWebApi.class);
    }

    public List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model) {
        return resource.getNextAvailableMonthsForDoctor(model);
    }

    public List<DayWebModel> getAvailableDaysForDoctorsMonth(AvailableTimeRequestModel model) {
        return resource.getAvailableDaysForDoctorsMonth(model);
    }

    public Map<String, List<SimpleAvailabilityWebModel>> getCalendarForDoctor(CalendarRequestModel model) {
        return resource.getCalendarForDoctor(model);
    }

    public SimpleVisitWebModel createNewVisit(SimpleVisitWebModel visitRequest) {
        return resource.createNewVisit(visitRequest);
    }

    public SimpleVisitWebModel alterVisitStatus(Long visitId, VisitStatusChangeModel visitStatusChangeModel) {
        return resource.alterVisitStatus(visitId, visitStatusChangeModel);
    }

    public SimplePage<SimpleVisitWebModel> getAllOwnVisits(VisitQueryModel model) {
        return resource.getAllOwnVisits(model);
    }

    public PatientHistoryPage getPatientsVisits(PatientHistoryQueryModel model) {
        return resource.getPatientsVisits(model);
    }

    public FullVisitWebModel getVisitDetails(Long visitId) {
        return resource.getVisitDetails(visitId);
    }

    public FullVisitWebModel alterVisit(Long visitId, FullVisitWebModel fullVisitWebModel) {
        return resource.alterVisit(visitId, fullVisitWebModel);
    }

    public SimpleVisitWebModel changeVisitDate(VisitDateChangeModel model) {
        return resource.changeVisitDate(model);
    }

    public void removeVisit(Long id) {
        resource.removeVisit(id);
    }
}

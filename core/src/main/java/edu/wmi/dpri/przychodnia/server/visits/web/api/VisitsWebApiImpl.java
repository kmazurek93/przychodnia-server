package edu.wmi.dpri.przychodnia.server.visits.web.api;

import edu.wmi.dpri.przychodnia.commons.visits.webapi.VisitsWebApi;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitWebModel;
import edu.wmi.dpri.przychodnia.server.visits.web.service.VisitsWebService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lupus on 17.12.16.
 */
public class VisitsWebApiImpl implements VisitsWebApi {
    @Inject
    private VisitsWebService visitsWebService;

    @Override
    public List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model) {
        return visitsWebService.getNextAvailableMonthsForDoctor(model);
    }

    @Override
    public List<DayWebModel> getAvailableDaysForDoctorsMonth(AvailableTimeRequestModel model) {
        return null;
    }

    @Override
    public List<VisitWebModel> getVisitsOfDoctor(AvailableTimeRequestModel model) {
        return null;
    }
}

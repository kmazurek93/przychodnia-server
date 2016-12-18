package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.server.visits.service.DoctorAvailabilityService;
import edu.wmi.dpri.przychodnia.server.visits.service.TimeService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 17.12.16.
 */
@Component
public class VisitsWebService {

    @Inject
    private DoctorAvailabilityService doctorAvailabilityService;
    @Inject
    private TimeService timeService;

    public List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model) {
        List<MonthWebModel> outcome = newArrayList();
        DateTime dateTime = new DateTime(model.getDate());
        for (int i = 0; i < 6; i++) {
            MonthWebModel monthWebModel = new MonthWebModel();
            DateTime beginningOfMonth = timeService
                    .getBeginningOfMonth(dateTime.plusMonths(i));
            monthWebModel
                    .setMonthStart(beginningOfMonth.getMillis());
            monthWebModel
                    .setAvailable(doctorAvailabilityService
                            .isAvailableOnMonth(model.getDoctorId(), beginningOfMonth));
            outcome.add(monthWebModel);
        }
        return outcome;
    }
}

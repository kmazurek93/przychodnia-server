package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
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
        List<MonthWebModel> monthWebModels = newArrayList();
        DateTime dateTime = new DateTime(model.getDate());
        for (int i = 0; i < 6; i++) {
            DateTime beginningOfMonth = timeService
                    .getBeginningOfMonth(dateTime.plusMonths(i));

            boolean availableOnMonth = doctorAvailabilityService
                    .isAvailableOnMonth(model.getDoctorId(), beginningOfMonth);
            MonthWebModel monthWebModel = new MonthWebModel();

            monthWebModel.setAvailable(availableOnMonth);
            monthWebModel.setMonthStart(beginningOfMonth.getMillis());
            monthWebModels.add(monthWebModel);
        }
        return monthWebModels;
    }

    public List<DayWebModel> getDayAvailabilityForDoctorAndMonth(AvailableTimeRequestModel model) {
        List<DayWebModel> dayWebModels = newArrayList();
        DateTime dateTime = new DateTime(model.getDate());
        DateTime beginningOfMonth = timeService.getBeginningOfMonth(dateTime);
        DateTime endOfMonth = timeService.getEndOfMonth(dateTime);
        for (int i = 0; i < endOfMonth.getDayOfMonth(); i++) {
            DateTime date = beginningOfMonth.plusDays(i);
            DayWebModel dayWebModel = new DayWebModel();
            dayWebModel.setAvailable(doctorAvailabilityService
                    .isAvailableOnMonth(model.getDoctorId(), date));
            dayWebModel.setDayStart(date.getMillis());
            dayWebModels.add(dayWebModel);
        }
        return dayWebModels;
    }
}

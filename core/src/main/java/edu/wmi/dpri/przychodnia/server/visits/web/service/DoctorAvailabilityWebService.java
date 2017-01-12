package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.server.visits.function.DoctorCalendarToSimpleAvailabilityModelFunction;
import edu.wmi.dpri.przychodnia.server.visits.service.DoctorAvailabilityService;
import edu.wmi.dpri.przychodnia.server.visits.service.TimeService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@Component
public class DoctorAvailabilityWebService {

    @Inject
    private DoctorAvailabilityService doctorAvailabilityService;
    @Inject
    private TimeService timeService;

    @Inject
    private DoctorCalendarToSimpleAvailabilityModelFunction function;
    @PersistenceContext
    private EntityManager entityManager;

    public List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model) {
        List<MonthWebModel> monthWebModels = newArrayList();
        DateTime dateTime = new DateTime(model.getStartDate());
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
        DateTime dateTime = new DateTime(model.getStartDate());
        DateTime beginningOfMonth = timeService.getBeginningOfMonth(dateTime);
        DateTime endOfMonth = timeService.getEndOfMonth(dateTime);
        for (int i = 0; i < endOfMonth.getDayOfMonth(); i++) {
            DateTime date = beginningOfMonth.plusDays(i);
            DayWebModel dayWebModel = new DayWebModel();
            dayWebModel.setAvailable(doctorAvailabilityService
                    .isAvailableOnDay(model.getDoctorId(), date));
            dayWebModel.setDayStart(date.getMillis());
            dayWebModels.add(dayWebModel);
        }
        return dayWebModels;
    }



}

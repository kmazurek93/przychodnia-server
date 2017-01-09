package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.CalendarRequestModel;
import edu.wmi.dpri.przychodnia.server.entity.procedures.DoctorCalendar;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 18.12.16.
 */
@Component
public class DoctorAvailabilityService {

    public static final String FREE = "free";
    @Inject
    private DoctorCalendarService doctorCalendarService;
    @Inject
    private NowProvider nowProvider;

    public boolean isAvailableOnMonth(Long doctorId, DateTime date) {
        DateTime dateEnd = date.dayOfMonth().withMaximumValue();

        if (date.isBefore(nowProvider.now()) && dateEnd.isBefore(nowProvider.now())) {
            return false;
        }

        CalendarRequestModel model = new CalendarRequestModel();
        model.setDoctorId(doctorId);
        model.setStartDate(date.getMillis());
        model.setEndDate(dateEnd.getMillis());
        List<DoctorCalendar> doctorCalendar = doctorCalendarService.getDoctorCalendar(model);

        List<DoctorCalendar> filtered = doctorCalendar.stream()
                .filter(o -> FREE.equals(o.getState())).collect(Collectors.toList());

        return filtered.size() > 0;

    }

    public boolean isAvailableOnDay(Long doctorId, DateTime day) {
        DateTime now = nowProvider.now();
        if (day.isBefore(now.minuteOfHour().withMinimumValue().hourOfDay().withMinimumValue())) {
            return false;
        }
        CalendarRequestModel model = new CalendarRequestModel();
        model.setDoctorId(doctorId);
        model.setStartDate(day.getMillis());
        model.setEndDate(day.getMillis());
        List<DoctorCalendar> doctorCalendar = doctorCalendarService.getDoctorCalendar(model);

        List<DoctorCalendar> filtered = doctorCalendar.stream()
                .filter(o -> FREE.equals(o.getState())).collect(Collectors.toList());

        return filtered.size() > 0;
    }
}

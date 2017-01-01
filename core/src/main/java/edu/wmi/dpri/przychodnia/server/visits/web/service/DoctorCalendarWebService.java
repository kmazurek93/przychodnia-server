package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.CalendarRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.visits.service.DoctorCalendarService;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class DoctorCalendarWebService {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    @Inject
    private DoctorCalendarService doctorCalendarService;


    public Map<String, List<SimpleAvailabilityWebModel>> getDoctorCalendar(CalendarRequestModel model) {
        Map<String, List<SimpleAvailabilityWebModel>> map = newHashMap();
        fillMapDays(map, model);
        List<SimpleAvailabilityWebModel> calendar = doctorCalendarService.getDoctorsCalendar(model);
        fillMapLists(map, calendar);
        return map;
    }

    private void fillMapLists(Map<String, List<SimpleAvailabilityWebModel>> map,
                              List<SimpleAvailabilityWebModel> calendar) {
        calendar.forEach(o -> map.get(o.getDate()).add(o));
    }

    private void fillMapDays(Map<String, List<SimpleAvailabilityWebModel>> map, CalendarRequestModel model) {
        DateTime startDate = new DateTime(model.getStartDate());
        DateTime endDate = new DateTime(model.getEndDate());
        Interval interval = new Interval(startDate, endDate);
        long days = interval.toDuration().getStandardDays();
        for (long i = 0; i <= days; i++) {
            DateTime key = startDate.plusDays((int) i);
            map.put(key.toString(YYYY_MM_DD), newArrayList());
        }
    }
}

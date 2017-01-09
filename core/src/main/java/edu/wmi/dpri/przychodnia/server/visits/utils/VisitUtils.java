package edu.wmi.dpri.przychodnia.server.visits.utils;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by khartv on 09.01.2017.
 */
public class VisitUtils {

    public void fillDates(Visit input, SimpleVisitWebModel outcome) {
        LocalDate date = input.getDate();
        DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 0, 0);
        LocalTime endTime = input.getTimeWindow().getEndTime();
        LocalTime startTime = input.getTimeWindow().getStartTime();
        DateTime endDate = dateTime.withHourOfDay(endTime.getHourOfDay()).withMinuteOfHour(endTime.getMinuteOfHour());
        DateTime startDate = dateTime.withHourOfDay(startTime.getHourOfDay()).withMinuteOfHour(startTime.getMinuteOfHour());
        outcome.setStart(startDate.getMillis());
        outcome.setEnd(endDate.getMillis());
    }
}

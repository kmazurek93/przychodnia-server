package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by lupus on 18.12.16.
 */
@Component
public class TimeService {

    @Inject
    private NowProvider nowProvider;

    public DateTime getBeginningOfMonth(DateTime day) {
        return day.dayOfMonth().withMinimumValue().withTime(0, 0, 0, 0);
    }

    public DateTime getEndOfMonth(DateTime day) {
        return day.dayOfMonth().withMaximumValue().withTime(23, 59, 59, 999);
    }

    public DateTime getSixMonthsLaterOnEndOfTheMonth(DateTime input) {
        int month = getSixthMontCountingFrom(input.getMonthOfYear());
        int year = getProperYearForEndDate(input.getYear(), input.getMonthOfYear());

        return input.withMonthOfYear(month)
                .withYear(year)
                .dayOfMonth().withMaximumValue()
                .withTime(23, 59, 59, 999);
    }


    private int getProperYearForEndDate(int year, int month) {
        if (month >= 8) {
            return year + 1;
        } else {
            return year;
        }
    }

    private int getSixthMontCountingFrom(int month) {
        int outcome = (month + 5) % 12;
        return outcome == 0 ? 12 : outcome;
    }
}

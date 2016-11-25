package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import org.joda.time.DateTime;

/**
 * Created by lupus on 25.11.16.
 */
public class DayWebModel {
    private DateTime dayStart;
    private Boolean available;

    public DateTime getDayStart() {
        return dayStart;
    }

    public void setDayStart(DateTime dayStart) {
        this.dayStart = dayStart;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

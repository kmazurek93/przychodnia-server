package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import org.joda.time.DateTime;

/**
 * Created by lupus on 25.11.16.
 */
public class MonthWebModel {
    private DateTime monthStart;
    private Boolean available;

    public DateTime getMonthStart() {
        return monthStart;
    }

    public void setMonthStart(DateTime monthStart) {
        this.monthStart = monthStart;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

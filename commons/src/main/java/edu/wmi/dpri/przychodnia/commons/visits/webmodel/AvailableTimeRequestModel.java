package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import org.joda.time.DateTime;

/**
 * Created by lupus on 25.11.16.
 */
public class AvailableTimeRequestModel {
    private Long doctorId;
    private DateTime date;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

}

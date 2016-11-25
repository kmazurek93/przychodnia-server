package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import org.joda.time.DateTime;

/**
 * Created by lupus on 25.11.16.
 */
public class VisitWebModel {
    private Long visitId;
    private Long doctorId;
    private String patientPesel;
    private Boolean available;
    private String patientName;
    private DateTime start;
    private DateTime end;


}

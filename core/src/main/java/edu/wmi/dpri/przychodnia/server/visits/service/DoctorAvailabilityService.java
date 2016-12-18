package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by lupus on 18.12.16.
 */
@Component
public class DoctorAvailabilityService {

    @Inject
    private TimeWindowCountService timeWindowCountService;
    @Inject
    private VisitService visitService;

    public boolean isAvailableOnMonth(Long doctorId, DateTime date) {
        Long maximumVisitCountForDoctorAndMonth =
                timeWindowCountService.getMaximumVisitCountForDoctorAndMonth(doctorId, date);
        Long savedVisitsOfDoctor = visitService.countVisitsByDoctorOnMonth(doctorId, date);

        return (maximumVisitCountForDoctorAndMonth - savedVisitsOfDoctor) > 0;
    }

}

package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by lupus on 18.12.16.
 */
@Component
public class VisitService {

    @Inject
    private VisitRepository repository;
    @Inject
    private TimeService timeService;

    @Transactional(readOnly = true)
    public Long countVisitsByDoctorOnDay(Long doctorId, DateTime day) {
        DateTime beginning = timeService.getBeginningOfDay(day);
        DateTime end = timeService.getEndOfDay(day);
        return repository.countByDoctorIdAndDateBetween(doctorId, beginning, end);
    }

    @Transactional(readOnly = true)
    public Long countVisitsByDoctorOnMonth(Long doctorId, DateTime day) {
        DateTime beginning = timeService.getBeginningOfMonth(day);
        DateTime end = timeService.getEndOfMonth(day);
        return repository.countByDoctorIdAndDateBetween(doctorId, beginning, end);
    }
}

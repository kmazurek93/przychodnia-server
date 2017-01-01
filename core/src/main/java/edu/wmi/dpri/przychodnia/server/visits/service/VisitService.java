package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitQueryModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.hibernate.Hibernate.initialize;
import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * Created by lupus on 18.12.16.
 */
@Component
public class VisitService {

    @Inject
    private VisitRepository repository;
    @Inject
    private TimeService timeService;
    @Inject
    private SecurityService securityService;
    @Inject
    private UserVerificationService userVerificationService;

    private static final Sort sortByDateAndTimeWindow = new Sort(ASC, "date", "timeWindow.startTime");

    @Transactional(readOnly = true)
    public Long countVisitsByDoctorOnMonth(Long doctorId, DateTime day) {
        LocalDate beginning = new LocalDate(timeService.getBeginningOfMonth(day).getMillis());
        LocalDate end = new LocalDate(timeService.getEndOfMonth(day).getMillis());
        return repository.countByDoctorIdAndDateBetween(doctorId, beginning, end);
    }

    @Transactional(readOnly = true)
    public Visit findById(Long id) {
        Visit visit = repository.findOne(id);
        NotFoundExceptionThrower.throwExceptionIfNull(id, visit, ExceptionCause.RETRIEVAL, Visit.class);
        initializeChildEntities(visit);
        return visit;

    }

    @Transactional(readOnly = true)
    public List<Visit> getOwnDoctorVisits(VisitQueryModel model) {

        List<Visit> result = repository
                .findByDoctorIdAndDateAfterAndStatus(model.getDoctorId(),
                        new LocalDate(model.getFrom()),
                        model.getStatus(),
                        sortByDateAndTimeWindow);
        initializeChildEntities(result);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Visit> getOwnPatientVisits(VisitQueryModel model) {
        List<Visit> result = repository
                .findByPatientIdAndDateAfterAndStatus(model.getPatientId(),
                        new LocalDate(model.getFrom()),
                        model.getStatus(),
                        sortByDateAndTimeWindow);
        initializeChildEntities(result);
        return result;
    }

    private void initializeChildEntities(Visit o) {
        initialize(o.getDoctor());
        initialize(o.getTimeWindow());
        initialize(o.getPatient());
        initialize(o.getPatient().getPerson());
        initialize(o.getAssociatedVisit());
        initialize(o.getVisitsAssociatedWith());
    }

    private void initializeChildEntities(List<Visit> visits) {
        visits.forEach(this::initializeChildEntities);
    }


}

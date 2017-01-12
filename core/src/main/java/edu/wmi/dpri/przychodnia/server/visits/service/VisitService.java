package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.FullVisitWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.PatientHistoryQueryModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitQueryModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitStatusChangeModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static org.hibernate.Hibernate.initialize;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;


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
    private static final Sort sortByDateAndTimeWindowDesc = new Sort(DESC, "date", "timeWindow.startTime");

    @Transactional(readOnly = true)
    public Visit findById(Long id) {
        Visit visit = repository.findOne(id);
        throwExceptionIfNull(id, visit, ExceptionCause.RETRIEVAL, Visit.class);
        initializeChildEntities(visit);
        return visit;

    }

    @Transactional(readOnly = true)
    public Page<Visit> getOwnDoctorVisits(VisitQueryModel model) {

        LocalDate from = new LocalDate(model.getFrom()).minusDays(1);
        Page<Visit> result = repository
                .findByDoctorIdAndDateAfterAndStatus(model.getDoctorId(),
                        from,
                        model.getStatus(),
                        new PageRequest(model.getPage(), model.getSize(), sortByDateAndTimeWindow));
        initializeChildEntities(result.getContent());
        return result;
    }

    @Transactional(readOnly = true)
    public Page<Visit> getOwnPatientVisits(VisitQueryModel model) {
        LocalDate from = new LocalDate(model.getFrom()).minusDays(1);
        Page<Visit> result = repository
                .findByPatientIdAndDateAfterAndStatus(model.getPatientId(),
                        from,
                        model.getStatus(),
                        new PageRequest(model.getPage(), model.getSize(), sortByDateAndTimeWindow));
        initializeChildEntities(result.getContent());
        return result;
    }

    private void initializeChildEntities(Visit o) {
        initialize(o.getDoctor());
        initialize(o.getTimeWindow());
        initialize(o.getPatient());
        initialize(o.getPatient().getPerson());
        initialize(o.getDoctor().getEmployee());
        initialize(o.getDoctor().getEmployee().getPerson());
        initialize(o.getAssociatedVisit());
        initialize(o.getVisitsAssociatedWith());
    }

    private void initializeChildEntities(List<Visit> visits) {
        visits.forEach(this::initializeChildEntities);
    }

    @Transactional
    public Page<Visit> getPatientHistory(PatientHistoryQueryModel model) {

        Page<Visit> result = repository
                .findByPatientPersonPESELAndStatus(
                        model.getPesel(),
                        VisitStatusType.HAPPENED,
                        new PageRequest(model.getPage(),
                                model.getSize(),
                                sortByDateAndTimeWindowDesc));
        initializeChildEntities(result.getContent());
        return result;
    }

    @Transactional
    public Visit updateVisit(FullVisitWebModel fullVisitWebModel, boolean isAdminOrStaff) {
        Visit visit = repository.findOne(fullVisitWebModel.getVisitId());
        throwExceptionIfNull(fullVisitWebModel.getVisitId(), visit, ExceptionCause.MODIFICATION, Visit.class);
        if (isNotOwnedByDoctor(visit) && !isAdminOrStaff) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("VISIT_NOT_OWNED");
            throw new ForbiddenException(errorMessage);
        }
        visit.setComment(fullVisitWebModel.getComment());
        visit.setStatus(fullVisitWebModel.getStatus());
        Visit saved = repository.save(visit);
        initializeChildEntities(saved);
        return visit;
    }

    private boolean isNotOwnedByDoctor(Visit visit) {
        UserContext userContext = getUserContext();
        Long contextDoctorId = userContext.getDoctorId();
        Long visitDoctorId = visit.getDoctor().getId();
        return !visitDoctorId.equals(contextDoctorId);
    }

    private boolean isNotOwnedByPatient(Visit visit) {
        UserContext userContext = getUserContext();
        Long contextPatientId = userContext.getPatientId();
        Long visitPatientId = visit.getPatient().getId();
        return !visitPatientId.equals(contextPatientId);
    }

    private UserContext getUserContext() {
        return securityService.getUserContextFromContextHolder();
    }

    @Transactional
    public void removeVisit(Long id) {
        Visit visit = repository.findOne(id);
        throwExceptionIfNull(id, visit, ExceptionCause.DELETION, Visit.class);
        if (isNotOwnedByDoctor(visit) && isNotOwnedByPatient(visit)) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("VISIT_NOT_OWNED");
            throw new ForbiddenException(errorMessage);
        }
        unlinkAllVisits(visit);
        repository.delete(id);
    }

    private void unlinkAllVisits(Visit visit) {
        visit.setAssociatedVisit(null);
        visit.setVisitsAssociatedWith(newArrayList());
        repository.save(visit);
    }


    public Visit changeStatus(Long id, VisitStatusChangeModel visitStatusChangeModel) {
        Visit visit = repository.findOne(id);
        throwExceptionIfNull(id, visit, ExceptionCause.MODIFICATION, Visit.class);
        if(isNotOwnedByDoctor(visit)) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("VISIT_NOT_OWNED");
            throw new ForbiddenException(errorMessage);
        }
        visit.setStatus(visitStatusChangeModel.getStatus());
        return repository.save(visit);
    }
}

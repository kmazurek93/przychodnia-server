package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitDateChangeModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_ADMIN;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_DOCTOR;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_STAFF;
import static org.hibernate.Hibernate.initialize;

/**
 * Created by lupus on 01.01.17.
 */
@Component
public class VisitDateChangeService {
    @Inject
    private VisitRepository visitRepository;
    @Inject
    private VisitCreationAndAvailabilityService visitCreationAndAvailabilityService;
    @Inject
    private SecurityService securityService;
    @Inject
    private UserVerificationService userVerificationService;

    @Transactional
    public Visit changeVisitDate(VisitDateChangeModel model) {
        boolean isStaff = userVerificationService.verifyIfHasAnyAuthorityOf(newArrayList(ROLE_ADMIN, ROLE_DOCTOR, ROLE_STAFF));
        boolean isDoctor = userVerificationService.verifyIfHasAuthority(ROLE_DOCTOR);
        Visit visit = visitRepository.findOne(model.getVisitId());
        throwExceptionIfNull(model.getVisitId(), visit, ExceptionCause.MODIFICATION, Visit.class);
        doEligibilityVerification(isStaff, isDoctor, visit);
        doStatusVerification(visit);
        SimpleVisitWebModel visitRequest = new SimpleVisitWebModel();
        visitRequest.setStart(model.getNewStartDate());
        visitRequest.setDoctorId(visit.getDoctor().getId());
        visitRequest.setPatientPesel(visit.getPatient().getPerson().getPESEL());
        Visit newVisit = visitCreationAndAvailabilityService.createNewVisit(visitRequest);
        newVisit.setAssociatedVisit(visit.getAssociatedVisit());
        newVisit.setVisitsAssociatedWith(visit.getVisitsAssociatedWith());
        newVisit.setComment(visit.getComment());
        visitRepository.delete(visit.getId());
        newVisit = visitRepository.save(newVisit);
        initialize(newVisit.getDoctor());
        initialize(newVisit.getTimeWindow());
        initialize(newVisit.getPatient());
        initialize(newVisit.getPatient().getPerson());
        initialize(newVisit.getAssociatedVisit());
        initialize(newVisit.getVisitsAssociatedWith());
        return newVisit;
    }

    private void doStatusVerification(Visit visit) {
        if (visit.getStatus().equals(VisitStatusType.HAPPENED)
                || visit.getStatus().equals(VisitStatusType.CANCELLED)) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("DATE_CANNOT_BE_CHANGED_VISIT_IS_NOT_NEW");
            throw new ForbiddenException(errorMessage);
        }
    }

    private void doEligibilityVerification(boolean isStaff, boolean isDoctor, Visit visit) {
        if (!isStaff) {
            if (isNotOwnedByPatient(visit)) {
                ErrorMessage errorMessage = getForbiddenErrorMessage("VISIT_NOT_OWNED");
                throw new ForbiddenException(errorMessage);
            }
            if (isDoctor) {
                if (isNotOwnedByDoctor(visit)) {
                    ErrorMessage errorMessage = getForbiddenErrorMessage("VISIT_NOT_OWNED");
                    throw new ForbiddenException(errorMessage);
                }
            }
        }
    }

    private UserContext getUserContext() {
        return securityService.getUserContextFromContextHolder();
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
}

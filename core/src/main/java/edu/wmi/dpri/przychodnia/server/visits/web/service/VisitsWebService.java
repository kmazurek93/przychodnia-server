package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.*;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import edu.wmi.dpri.przychodnia.server.visits.function.VisitToFullDetailsModelFunction;
import edu.wmi.dpri.przychodnia.server.visits.function.VisitToSimpleWebModelFunction;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitCreationAndAvailabilityService;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitDateChangeService;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.*;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class VisitsWebService {
    @Inject
    private VisitService visitService;
    @Inject
    private SecurityService securityService;
    @Inject
    private VisitToSimpleWebModelFunction function;
    @Inject
    private UserVerificationService userVerificationService;
    @Inject
    private VisitCreationAndAvailabilityService visitCreationAndAvailabilityService;
    @Inject
    private VisitToFullDetailsModelFunction fullDetailsModelFunction;
    @Inject
    private VisitDateChangeService visitDateChangeService;

    public List<SimpleVisitWebModel> getOwnVisits(VisitQueryModel model) {
        List<Visit> visits;
        switch (model.getType()) {
            case "DOCTOR":
                visits = getOwnDoctorVisits(model);
                break;
            case "PATIENT":
                visits = getOwnPatientVisits(model);
                break;
            default:
                visits = getOwnPatientVisits(model);
        }
        return function.convertAll(visits);
    }

    private List<Visit> getOwnDoctorVisits(VisitQueryModel model) {
        boolean isDoctor = userVerificationService.verifyIfHasAuthority(ROLE_DOCTOR);
        UserContext userContext = getUserContext();
        if (isDoctor && userContext.getDoctorId() != null) {
            model.setDoctorId(userContext.getDoctorId());
            return visitService.getOwnDoctorVisits(model);
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("IS_NOT_A_DOCTOR");
        throw new ForbiddenException(errorMessage);
    }

    private List<Visit> getOwnPatientVisits(VisitQueryModel model) {
        boolean isPatient = userVerificationService.verifyIfHasAuthority(ROLE_PATIENT);
        UserContext userContext = getUserContext();
        if (isPatient && userContext.getPatientId() != null) {
            model.setPatientId(userContext.getPatientId());
            return visitService.getOwnPatientVisits(model);
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("IS_NOT_A_PATIENT");
        throw new ForbiddenException(errorMessage);
    }

    private UserContext getUserContext() {
        return securityService.getUserContextFromContextHolder();
    }

    public SimpleVisitWebModel createNewVisit(SimpleVisitWebModel visitRequest) {
        Visit visit;
        boolean isDoctorOrAdmin = userVerificationService.verifyIfHasAnyAuthorityOf(newArrayList(ROLE_DOCTOR, ROLE_STAFF, ROLE_ADMIN));
        if (isDoctorOrAdmin) {
            visit = visitCreationAndAvailabilityService.createNewVisit(visitRequest);
        } else {
            boolean isPatient = userVerificationService.verifyIfHasAuthority(ROLE_PATIENT);
            if (isPatient) {
                UserContext userContext = getUserContext();
                visitRequest.setPatientPesel(userContext.getPesel());
                visit = visitCreationAndAvailabilityService.createNewVisit(visitRequest);
            } else {
                ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
                throw new ForbiddenException(errorMessage);
            }
        }
        return function.apply(visit);
    }

    public FullVisitWebModel getVisitDetails(Long visitId) {
        Visit visit = visitService.findById(visitId);
        verifyOwnership(visit);
        return fullDetailsModelFunction.apply(visit);
    }

    private void verifyOwnership(Visit visit) {
        UserContext userContext = getUserContext();
        Long patientId = visit.getPatient().getId();
        Long doctorId = visit.getDoctor().getId();
        boolean isStaff = userVerificationService.verifyIfHasAnyAuthorityOf(newArrayList(ROLE_ADMIN, ROLE_DOCTOR, ROLE_STAFF));
        if (!patientId.equals(userContext.getPatientId()) && !doctorId.equals(userContext.getDoctorId()) && !isStaff) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
            throw new ForbiddenException(errorMessage);
        }

    }

    public PatientHistoryPage getPatientHistory(PatientHistoryQueryModel model) {
        boolean isEligible = userVerificationService.verifyIfHasAnyAuthorityOf(newArrayList(ROLE_ADMIN, ROLE_DOCTOR, ROLE_STAFF));
        if (isEligible) {
            Page<Visit> visits = visitService.getPatientHistory(model);
            PatientHistoryPage page = new PatientHistoryPage();
            page.setVisits(function.convertAll(visits.getContent()));
            page.setNumberOfPages(visits.getTotalPages());
            return page;
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
        throw new ForbiddenException(errorMessage);
    }

    public FullVisitWebModel alterVisit(Long visitId, FullVisitWebModel fullVisitWebModel) {
        boolean isAdminOrStaff = userVerificationService.verifyIfHasAnyAuthorityOf(newArrayList(ROLE_ADMIN, ROLE_STAFF));
        boolean isDoctor = userVerificationService.verifyIfHasAuthority(ROLE_DOCTOR);
        if (isAdminOrStaff || isDoctor) {
            fullVisitWebModel.setVisitId(visitId);
            Visit visit = visitService.updateVisit(fullVisitWebModel, isAdminOrStaff);
            return fullDetailsModelFunction.apply(visit);
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
        throw new ForbiddenException(errorMessage);
    }

    public void removeVisit(Long id) {
        visitService.removeVisit(id);
    }

    public SimpleVisitWebModel changeVisitDate(VisitDateChangeModel model) {
        Visit visit = visitDateChangeService.changeVisitDate(model);
        return function.apply(visit);
    }

    public SimpleVisitWebModel changeStatus(Long visitId, VisitStatusChangeModel visitStatusChangeModel) {
        boolean isDoctor = userVerificationService.verifyIfHasAuthority(ROLE_DOCTOR);
        if (isDoctor) {
            Visit visit = visitService.changeStatus(visitId, visitStatusChangeModel);
            return function.apply(visit);
        }
        ErrorMessage errorMessage = getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
        throw new ForbiddenException(errorMessage);
    }
}

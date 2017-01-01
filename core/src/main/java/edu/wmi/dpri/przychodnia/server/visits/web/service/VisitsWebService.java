package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitQueryModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import edu.wmi.dpri.przychodnia.server.visits.function.VisitToSimpleWebModelFunction;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitCreationService;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
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
    private VisitCreationService visitCreationService;

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
        ErrorMessage errorMessage = ErrorMessageGenerator.getForbiddenErrorMessage("IS_NOT_A_DOCTOR");
        throw new ForbiddenException(errorMessage);
    }

    private List<Visit> getOwnPatientVisits(VisitQueryModel model) {
        boolean isPatient = userVerificationService.verifyIfHasAuthority(ROLE_PATIENT);
        UserContext userContext = getUserContext();
        if (isPatient && userContext.getPatientId() != null) {
            model.setPatientId(userContext.getPatientId());
            return visitService.getOwnPatientVisits(model);
        }
        ErrorMessage errorMessage = ErrorMessageGenerator.getForbiddenErrorMessage("IS_NOT_A_PATIENT");
        throw new ForbiddenException(errorMessage);
    }

    private UserContext getUserContext() {
        return securityService.getUserContextFromContextHolder();
    }

    public SimpleVisitWebModel createNewVisit(SimpleVisitWebModel visitRequest) {
        Visit visit;
        boolean isDoctorOrAdmin = userVerificationService.verifyIfHasAnyAuthorityOf(newArrayList(ROLE_DOCTOR, ROLE_STAFF, ROLE_ADMIN));
        if(isDoctorOrAdmin) {
             visit = visitCreationService.createNewVisit(visitRequest);
        } else {
            boolean isPatient = userVerificationService.verifyIfHasAuthority(ROLE_PATIENT);
            if(isPatient) {
                UserContext userContext = getUserContext();
                visitRequest.setPatientPesel(userContext.getPesel());
                visit = visitCreationService.createNewVisit(visitRequest);
            } else {
                ErrorMessage errorMessage = ErrorMessageGenerator.getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
                throw new ForbiddenException(errorMessage);
            }
        }
        return function.apply(visit);
    }

    //TODO implement visit: create, accept/decline, remove, edit, doctor's edit
}

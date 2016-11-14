package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.UserLinkingUnlinkingWebModel;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserConnectionHelperService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.STAFF_OR_ADMIN;

/**
 * Created by lupus on 29.10.16.
 */
@Service
public class UserConnectionWebService {

    private static final String INSUFFICIENT_PRIVILEGES = "INSUFFICIENT_PRIVILEGES";

    @Inject
    private UserVerificationService userVerificationService;
    @Inject
    private UserConnectionHelperService userConnectionHelperService;

    public void connectUsers(UserLinkingUnlinkingWebModel model) {
        boolean isEligible = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        if (isEligible) {
            userConnectionHelperService.connectUsers(model);
        } else {
            throwForbiddenException();
        }
    }

    public void removeConnection(UserLinkingUnlinkingWebModel model) {
        boolean isEligible = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        if (isEligible) {
            userConnectionHelperService.unlinkUsers(model);
        } else {
            throwForbiddenException();
        }
    }

    private void throwForbiddenException() {
        ErrorMessage errorMessage = ErrorMessageGenerator.getForbiddenErrorMessage(INSUFFICIENT_PRIVILEGES);
        throw new ForbiddenException(errorMessage);
    }
}

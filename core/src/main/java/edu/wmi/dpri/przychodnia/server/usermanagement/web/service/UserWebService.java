package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.*;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.UnauthorizedException;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserRegisteringStateFunctions;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserToUserCrudWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserToUserDataSimpleModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.UserSearchResultBuilder.anUserSearchResult;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.STAFF_OR_ADMIN;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class UserWebService {

    public static final String INSUFFICIENT_PRIVILEGES = "INSUFFICIENT_PRIVILEGES";
    public static final ErrorMessage FORBIDDEN_ERROR_MESSAGE = getForbiddenErrorMessage(INSUFFICIENT_PRIVILEGES);
    @Inject
    private UserRegisteringStateFunctions userRegisteringStateFunctions;

    @Inject
    private UserService userService;

    @Inject
    private UserToUserDataSimpleModelFunction simpleModelFunction;

    @Inject
    private UserVerificationService userVerificationService;
    @Inject
    private UserToUserCrudWebModelFunction crudWebModelFunction;

    public void handleAddingUserDuringRegistration(UserRegisteringState state) {
        User user = userRegisteringStateFunctions.createUserToSaveFromState(state);
        User savedUser = userService.saveUser(user);
        state.setSavedUser(savedUser);
    }

    public void updateUserData(UserDataWebModel userDataWebModel) {
//TODO
    }

    public UserCrudWebModel getUserCompleteData(Long userId) {
        boolean isUserIdCorrect = userVerificationService.verifyIfIdIsEqual(userId);
        boolean isAdminOrStaff = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        if (isAdminOrStaff || isUserIdCorrect) {
            return crudWebModelFunction.apply(userService.getUserById(userId));
        } else {
            throw new UnauthorizedException(FORBIDDEN_ERROR_MESSAGE);
        }
    }

    public UserSearchResult queryUsers(UserSearchWebModel model) {
        boolean isEligible = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        if (isEligible) {
            if (model == null) {
                List<UserDataSimpleModel> userDataSimpleModels =
                        simpleModelFunction.applyToList(userService.getAllInitializedUsers());
                return anUserSearchResult().withUsers(userDataSimpleModels).withAmountOfPages(-1).build();
            } else {
                //TODO handle searching. <scheduled 18.11 - 20.11>
                return null;
            }
        } else {
            throw new UnauthorizedException(FORBIDDEN_ERROR_MESSAGE);
        }
    }

    public void archivizeUser(Long id) {
        boolean isUserIdCorrect = userVerificationService.verifyIfIdIsEqual(id);
        boolean isAdminOrStaff = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        if (isAdminOrStaff || isUserIdCorrect) {
            userService.archivizeUser(id);
        } else {
            throw new UnauthorizedException(FORBIDDEN_ERROR_MESSAGE);
        }
    }
}

package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchResult;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.entity.views.BaseUserData;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.UnauthorizedException;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.BaseUserDataToUserDataSimpleModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserRegisteringStateFunctions;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserToUserCrudWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserAllDataUpdateService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

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
    private UserAllDataUpdateService userAllDataUpdateService;
    @Inject
    private UserVerificationService userVerificationService;
    @Inject
    private UserToUserCrudWebModelFunction crudWebModelFunction;
    @Inject
    private UserSearchWebService userSearchWebService;
    @Inject
    private BaseUserDataToUserDataSimpleModelFunction userDataSimpleModelFunction;

    public void handleAddingUserDuringRegistration(UserRegisteringState state) {
        User user = userRegisteringStateFunctions.createUserToSaveFromState(state);
        User savedUser = userService.saveUser(user);
        state.setSavedUser(savedUser);
    }

    public UserCrudWebModel updateUserData(Long pathId, UserCrudWebModel userCrudWebModel) {
        userAllDataUpdateService.updateUserData(pathId, userCrudWebModel);
        return crudWebModelFunction.apply(userService.getUserById(pathId));
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
        boolean isAdminOrStaff = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        Page<? extends BaseUserData> result;
        if (isAdminOrStaff) {
            if (model.getSearchType().equals("PATIENT")) {
                result = userSearchWebService.queryPatients(model);
            } else if (model.getSearchType().equals("STAFF")) {
                result = userSearchWebService.queryStaff(model);
            } else {
                result = userSearchWebService.queryAll(model);
            }
        } else {
            result = userSearchWebService.queryStaff(model);
        }
        return anUserSearchResult().withUsers(userDataSimpleModelFunction.convertAll(result.getContent()))
                .withAmountOfPages(result.getTotalPages()).build();
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
